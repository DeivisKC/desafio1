/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deivis.desafio1.perfil;

import deivis.desafio1.postagem.Postagem;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author deivis
 */
public class PerfilPagina extends Perfil
{
    private int quantidadePostagens;
    private int quantidadeSeguindo;
    private int quantidadeSeguidores;
    private List<Postagem> postagens;

    public PerfilPagina()
    {
        
    }

    public PerfilPagina(int quantidadePostagens, int quantidadeSeguindo, int quantidadeSeguidores, List<Postagem> postagens)
    {
        this.quantidadePostagens = quantidadePostagens;
        this.quantidadeSeguindo = quantidadeSeguindo;
        this.quantidadeSeguidores = quantidadeSeguidores;
        this.postagens = postagens;
    }

    public int getQuantidadePostagens()
    {
        return quantidadePostagens;
    }

    public void setQuantidadePostagens(int quantidadePostagens)
    {
        this.quantidadePostagens = quantidadePostagens;
    }

    public int getQuantidadeSeguindo()
    {
        return quantidadeSeguindo;
    }

    public void setQuantidadeSeguindo(int quantidadeSeguindo)
    {
        this.quantidadeSeguindo = quantidadeSeguindo;
    }

    public int getQuantidadeSeguidores()
    {
        return quantidadeSeguidores;
    }

    public void setQuantidadeSeguidores(int quantidadeSeguidores)
    {
        this.quantidadeSeguidores = quantidadeSeguidores;
    }

    public List<Postagem> getPostagens()
    {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens)
    {
        this.postagens = postagens;
    }
}