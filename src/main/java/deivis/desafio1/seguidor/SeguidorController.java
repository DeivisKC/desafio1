package deivis.desafio1.seguidor;

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

    //Lista todos os registros da tabela
    @GetMapping("/seguidor")
    List<Seguidor> todos()
    {
        return repository.findAll();
    }
    
    //Lista todos os usuários seguidos pelo usuário informado
    @GetMapping("/seguidor/usuarioseguidor/{usuarioSeguidor}")
    List<Seguidor> listaUsuarioSeguidor(@PathVariable String usuarioSeguidor)
    {
        return repository.findByUsuarioSeguidor(usuarioSeguidor);
    }
    
    //Lista todos os usuários que seguem o usuário informado
    @GetMapping("/seguidor/usuarioseguido/{usuarioSeguido}")
    List<Seguidor> listaUsuarioSeguido(@PathVariable String usuarioSeguido)
    {
        return repository.findByUsuarioSeguido(usuarioSeguido);
    }
    
    //Cadastro de um novo seguidor, ou seja, chama esse método pra "seguir" alguém
    @PostMapping("/seguidor")
    String novoSeguidor(@RequestBody Seguidor seguidor)
    {
        String ret = "";
        try
        {
            //O ID da tabela é seguidor_seguido, nunca vai repetir, tem verificação a seguir.
            seguidor.setId(seguidor.getUsuarioSeguidor().trim() + "_" + seguidor.getUsuarioSeguido().trim());
            seguidor.setDataSeguido(LocalDate.now());
            
            //Não pode seguir a si mesmo
            if (seguidor.getUsuarioSeguidor().trim().equals(seguidor.getUsuarioSeguido().trim()))
            {
                return "Erro! Não é possível seguir a sí mesmo.";
            }
            
            //Não pode seguir que o usuário já segue.
            if (repository.existsById(seguidor.getId()))
            {
                return "Erro! Usuário já seguido.";
            }
            
            Seguidor p_temp = repository.insert(seguidor);
            
            return p_temp.getId();
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    //Método para excluir seguidor, no caso, chamado para deixar de seguir alguém.
    @DeleteMapping("/seguidor/{id}")
    String deleteSeguidor(@PathVariable String id)
    {
        try
        {
            //Busca se o registro existe, pra só ai excluir ele.
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

    //Ver um registro apenas
    @GetMapping("/seguidor/{id}")
    Seguidor seguidorBuscar(@PathVariable String id)
    {
        Optional<Seguidor> post = repository.findById(id);
        return post.get();
    }
}
