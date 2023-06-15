package deivis.desafio1.perfil;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;

public class Perfil
{
    @Id
    private String usuario;
    private String nome;
    private String sobrenome;
    private LocalDate dataEntrada;

    Perfil()
    {
        
    }

    public Perfil(String usuario, String nome, String sobrenome, LocalDate dataEntrada, int quantidadePostagens)
    {
        this.usuario = usuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataEntrada = dataEntrada;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSobrenome()
    {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataEntrada()
    {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada)
    {
        this.dataEntrada = dataEntrada;
    }
}