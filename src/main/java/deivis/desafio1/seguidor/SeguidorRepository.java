/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deivis.desafio1.seguidor;

import deivis.desafio1.seguidor.*;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author deivis
 */
public interface SeguidorRepository extends MongoRepository<Seguidor, String>
{
//    public Seguidor findById(String id);
//    public List<Seguidor> findByTipo(String tipo);
    
//    public List<Seguidor> findBySeguidorData(LocalDate postagemData);
    
    public List<Seguidor> findByUsuarioSeguidor(String usuarioSeguidor);
    
    public List<Seguidor> findByUsuarioSeguido(String usuarioSeguido);
    
    @Query(value = "{usuarioSeguidor: ?0}", count = true)
    public int countUsuarioSeguidor(String usuarioSeguidor);
    
    @Query(value = "{usuarioSeguido: ?0}", count = true)
    public int countUsuarioSeguido(String usuarioSeguido);
}
