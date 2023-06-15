/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deivis.desafio1.perfil;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author deivis
 */
public interface PerfilRepository extends MongoRepository<Perfil, String>
{
//    public Perfil findById(String id);
//    public List<Perfil> findByTipo(String tipo);
    
//    public List<Perfil> findByPerfilData(LocalDate postagemData);
    
//    @Query(value = "{postagemData: ?0}", count = true)
//    public long countPerfilData(LocalDate postagemData);
}
