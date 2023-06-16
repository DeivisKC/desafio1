package deivis.desafio1.postagem;

import deivis.desafio1.seguidor.Seguidor;
import deivis.desafio1.seguidor.SeguidorRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostagemController
{
    @Autowired
    private PostagemRepository repository;
    
    @Autowired
    private SeguidorRepository seguidorRepository;

    PostagemController()
    {

    }

    //Lista todas as postagens
    @GetMapping("/postagem")
    List<Postagem> todos()
    {
        return repository.findAll();
    }
    
    //Lista todas as postagens, porém apenas dos usuários seguidos pelo usuário informado
    @GetMapping("/home/{usuarioSeguidor}")
    List<Postagem> todosPorUsuarioSeguidor(@PathVariable String usuarioSeguidor)
    {
        //Obtenho a lista dos usuários seguidos, pego apenas o nome dos usuários seguidos, e leio postagens daqueles usuários
        List<Seguidor> seguidor = seguidorRepository.findByUsuarioSeguidor(usuarioSeguidor);
        List<String> usuarios = seguidor.stream().map(Seguidor::getUsuarioSeguido).collect(Collectors.toList());
        return repository.findByUsuario(usuarios);
    }

    //Página "home", que é o feed de todas as postagens da plataforma
    @GetMapping("/home")
    List<Postagem> todosRegistros()
    {
        return repository.findAll();
    }
    
    //Lista as postagens por tipo: P - postagens / R - repostagem / C - postagens com comentário
    @GetMapping("/postagem/{tipo}")
    List<Postagem> todosPorTipo(@PathVariable String tipo)
    {
        return repository.findByTipo(tipo);
    }
    
    //"Postar" uma postagem
    //O mesmo método penso que deva ser chamado na repostagem e na postagem com comentário.
    //Ao meu ver, só muda o tipo da postagem, pois já tem um campo para o id da mensagem repostada.
    //Então é só o front chamar o mesmo método com outros dados no corpo.
    @PostMapping("/postagem")
    String novaPostagem(@RequestBody Postagem p)
    {
        String ret = "";
        try
        {
            p.setPostagemData(LocalDate.now());
            p.setPostagemHora(LocalTime.now());
            p.setId(p.gerarId()); //Gero o ID manualmente, com base no usuário e data+hora
            
            //Limite de 777 caracteres
            if (p.getTexto().trim().length() > 777)
            {
                return "Erro! Tamanho máximo da postagem excedido.";
            }
            
            //Limite máximo de 5 postagens por dia
            if(repository.countPostagemData(p.getUsuario(), p.getPostagemData()) >= 5)
            {
                return "Erro! Quantidade máxima de postagem diárias excedida.";
            }
            
            Postagem p_temp = repository.insert(p);
            
            //Retorna o ID da postagem criada
            return p_temp.getId().trim();
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    // Single item
    @GetMapping("/postagem/{id}")
    Postagem umaPostagem(@PathVariable String id)
    {
        Optional<Postagem> post = repository.findById(id);
        return post.get();
    }

    //Criei métodos de put e delete, na instrução dizia pra não fazer, mas deixei ai.
//    @PutMapping("/postagem/{id}")
//    Postagem atualizarPostagem(@RequestBody Postagem novaPostagem, @PathVariable String id)
//    {
//        return repository.save(novaPostagem);
//    }
//
//    @DeleteMapping("/postagem/{id}")
//    void deletarPostagem(@PathVariable String id)
//    {
//        repository.deleteById(id);
//    }
}
