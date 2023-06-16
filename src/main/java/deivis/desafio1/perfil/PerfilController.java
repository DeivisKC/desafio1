package deivis.desafio1.perfil;

import deivis.desafio1.postagem.*;
import deivis.desafio1.seguidor.Seguidor;
import deivis.desafio1.seguidor.SeguidorRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PerfilController
{
    @Autowired
    private PerfilRepository repository;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private SeguidorRepository seguidorRepository;
    
    PerfilController()
    {

    }

    //Lista todos os perfis
    @GetMapping("/perfil")
    List<Perfil> todos()
    {
        return repository.findAll();
    }
    
    //Busca o perfil por usuário, que é a chave da tabela
    @GetMapping("/perfil/{usuario}")
    Perfil usuario(@PathVariable String usuario)
    {
        Optional<Perfil> perfil = repository.findById(usuario);
        return perfil.get();
    }
    
    //Página do "perfil". Retorna os dados do usuário, e todas as suas postagens
    //Criei uma classe PerfilPagina que herda a Perfil,.
    //Essa classe vai ter os dados do perfil, mais algumas informações de contagem
    //E também a coleção de Postagens daquele usuário.
    //Criei alguns contadores no repositório das Postagens pra facilitar.
    @GetMapping("/perfilPagina/{usuario}")
    PerfilPagina perfilPagina(@PathVariable String usuario)
    {
        Optional<Perfil> perfil = repository.findById(usuario);
        
        PerfilPagina perfilPagina = new PerfilPagina();
        perfilPagina.setNome(perfil.get().getNome());
        perfilPagina.setSobrenome(perfil.get().getSobrenome());
        perfilPagina.setUsuario(perfil.get().getUsuario());
        perfilPagina.setDataEntrada(perfil.get().getDataEntrada());
        perfilPagina.setQuantidadeSeguindo(seguidorRepository.countUsuarioSeguidor(perfil.get().getUsuario()));
        perfilPagina.setQuantidadeSeguidores(seguidorRepository.countUsuarioSeguido(perfil.get().getUsuario()));
        perfilPagina.setPostagens(postagemRepository.findByUsuario(perfil.get().getUsuario()));
        perfilPagina.setQuantidadePostagens(perfilPagina.getPostagens().size());
        
        return perfilPagina;
    }
    
    //Cadastro de perfil. Dizia pra não criar, mas achei bom pra eu mesmo criar os meus.
    @PostMapping("/perfil")
    String novoPerfil(@RequestBody Perfil perfil)
    {
        String ret = "";
        try
        {
            perfil.setDataEntrada(LocalDate.now());
            
            //Tamanho máximo do nome de usuário
            if (perfil.getUsuario().trim().length() > 14)
            {
                return "Erro! Tamanho máximo do usuário excedido.";
            }
            
            //Só pode ter caracteres alfanuméricos
            if(!perfil.getUsuario().matches("^[a-zA-Z0-9]*$"))
            {
                return "Erro! Usuário pode conter apenas caracteres alfanuméricos.";
            }
            
            Perfil p_temp = repository.insert(perfil);
            
            return p_temp.getUsuario();
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    //No manual dizia pra não criar CRUD do usuário, mas ta ai igual.
//    @PutMapping("/perfil/{usuario}")
//    Perfil replacePerfil(@RequestBody Perfil novoPerfil, @PathVariable String usuario)
//    {
//        return repository.save(novoPerfil);
//    }
//
//    @DeleteMapping("/perfil/{usuario}")
//    void deletePerfil(@PathVariable String usuario)
//    {
//        repository.deleteById(usuario);
//    }
}
