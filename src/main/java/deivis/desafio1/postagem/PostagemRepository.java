/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deivis.desafio1.postagem;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author deivis
 */
public interface PostagemRepository extends MongoRepository<Postagem, String>
{
//    public Postagem findById(String id);
    public List<Postagem> findByTipo(String tipo);
    
    public List<Postagem> findByPostagemData(LocalDate postagemData);
    
    public List<Postagem> findByUsuario(String usuario);
    
    @Query(value = "{usuario: {'$in' : ?0 } }")
    public List<Postagem> findByUsuario(List<String> usuario);
    
    @Query(value = "{usuario: ?0, postagemData: ?1}", count = true)
    public long countPostagemData(String usuario, LocalDate postagemData);
    
    @Query(value = "{usuario: ?0}", count = true)
    public long countUsuario(String usuario);
}
