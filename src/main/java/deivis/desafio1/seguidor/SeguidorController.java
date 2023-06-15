package deivis.desafio1.seguidor;

import deivis.desafio1.seguidor.*;
import deivis.desafio1.postagem.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeguidorController
{
    @Autowired
    private SeguidorRepository repository;

    @Autowired
    private PostagemRepository postagemRepository;
    
    SeguidorController()
    {

    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/seguidor")
    List<Seguidor> todos()
    {
        return repository.findAll();
    }
    
    @GetMapping("/seguidor/usuarioseguidor/{usuarioSeguidor}")
    List<Seguidor> listaUsuarioSeguidor(@PathVariable String usuarioSeguidor)
    {
        return repository.findByUsuarioSeguidor(usuarioSeguidor);
    }
    
    @GetMapping("/seguidor/usuarioseguido/{usuarioSeguido}")
    List<Seguidor> listaUsuarioSeguido(@PathVariable String usuarioSeguido)
    {
        return repository.findByUsuarioSeguido(usuarioSeguido);
    }
    
    @PostMapping("/seguidor")
    String novoSeguidor(@RequestBody Seguidor seguidor)
    {
        String ret = "";
        try
        {
            seguidor.setId(seguidor.getUsuarioSeguidor().trim() + "_" + seguidor.getUsuarioSeguido().trim());
            seguidor.setDataSeguido(LocalDate.now());
            
            if (seguidor.getUsuarioSeguidor().trim().equals(seguidor.getUsuarioSeguido().trim()))
            {
                return "Erro! Não é possível seguir a sí mesmo.";
            }
            
            Seguidor p_temp = repository.insert(seguidor);
            
            return p_temp.getId();
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    @DeleteMapping("/seguidor/{id}")
    String deleteSeguidor(@PathVariable String id)
    {
        try
        {
            Boolean seguidor = repository.existsById(id);
            
            if(seguidor)
            {
                repository.deleteById(id);

                seguidor = repository.existsById(id);

                if(seguidor)
                {
                    return "Erro!";
                }
                else
                {
                    return "Sucesso!";
                }
            }
            else
            {
                return "Erro! Registro não encontrado.";
            }
            
        }
        catch(Exception ex)
        {
            return "Erro! " + ex.getMessage().trim();
        }
    }

//    // Single item
//    @GetMapping("/seguidor/{id}")
//    Seguidor one(@PathVariable String id)
//    {
//        Optional<Seguidor> post = repository.findById(id);
//        return post.get();
//    }

//    @PutMapping("/seguidor/{id}")
//    Seguidor replaceSeguidor(@RequestBody Seguidor newSeguidor, @PathVariable String id)
//    {
//        return newSeguidor;
//    }
//
//    @DeleteMapping("/seguidor/{id}")
//    void deleteSeguidor(@PathVariable String id)
//    {
//    }
}
