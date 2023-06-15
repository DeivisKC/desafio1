package deivis.desafio1.postagem;

import deivis.desafio1.seguidor.Seguidor;
import deivis.desafio1.seguidor.SeguidorRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/postagem")
    List<Postagem> todos()
    {
        return repository.findAll();
    }
    
    @GetMapping("/home/{usuarioSeguidor}")
    List<Postagem> todosPorUsuarioSeguidor(@PathVariable String usuarioSeguidor)
    {
        List<Seguidor> seguidor = seguidorRepository.findByUsuarioSeguidor(usuarioSeguidor);
        List<String> usuarios = seguidor.stream().map(Seguidor::getUsuarioSeguido).collect(Collectors.toList());
        return repository.findByUsuario(usuarios);
    }
    
    @GetMapping("/home")
    List<Postagem> todosRegistros()
    {
        return repository.findAll();
    }
    
    @GetMapping("/postagem/{tipo}")
    List<Postagem> todosPorTipo(@PathVariable String tipo)
    {
        return repository.findByTipo(tipo);
    }
    
    @PostMapping("/postagem")
    String novaPostagem(@RequestBody Postagem p)
    {
        String ret = "";
        try
        {
            p.setPostagemData(LocalDate.now());
            p.setPostagemHora(LocalTime.now());
            p.setId(p.gerarId());
            
            if (p.getTexto().trim().length() > 777)
            {
                return "Erro! Tamanho máximo da postagem excedido.";
            }
            
            
            if(repository.countPostagemData(p.getUsuario(), p.getPostagemData()) >= 5)
            {
                return "Erro! Quantidade máxima de postagem diárias excedida.";
            }
            
            Postagem p_temp = repository.insert(p);
            
            return p_temp.getId().trim();
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

//    // Single item
//    @GetMapping("/postagem/{id}")
//    Postagem one(@PathVariable String id)
//    {
//        Optional<Postagem> post = repository.findById(id);
//        return post.get();
//    }

//    @PutMapping("/postagem/{id}")
//    Postagem replacePostagem(@RequestBody Postagem newPostagem, @PathVariable String id)
//    {
//        return newPostagem;
//    }
//
//    @DeleteMapping("/postagem/{id}")
//    void deletePostagem(@PathVariable String id)
//    {
//    }
}
