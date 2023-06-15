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

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/perfil")
    List<Perfil> todos()
    {
        return repository.findAll();
    }
    
    @GetMapping("/perfil/{usuario}")
    Perfil usuario(@PathVariable String usuario)
    {
        Optional<Perfil> perfil = repository.findById(usuario);
        return perfil.get();
    }
    
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
    
    @PostMapping("/perfil")
    String novoPerfil(@RequestBody Perfil perfil)
    {
        String ret = "";
        try
        {
            perfil.setDataEntrada(LocalDate.now());
            
            if (perfil.getUsuario().trim().length() > 14)
            {
                return "Erro! Tamanho máximo do usuário excedido.";
            }
            
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

//    // Single item
//    @GetMapping("/perfil/{id}")
//    Perfil one(@PathVariable String id)
//    {
//        Optional<Perfil> post = repository.findById(id);
//        return post.get();
//    }

//    @PutMapping("/perfil/{id}")
//    Perfil replacePerfil(@RequestBody Perfil newPerfil, @PathVariable String id)
//    {
//        return newPerfil;
//    }
//
//    @DeleteMapping("/perfil/{id}")
//    void deletePerfil(@PathVariable String id)
//    {
//    }
}
