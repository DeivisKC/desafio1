package deivis.desafio1.seguidor;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;

public class Seguidor
{
    @Id
    private String id;
    private String usuarioSeguidor;
    private String usuarioSeguido;
    private LocalDate dataSeguido;

    public Seguidor()
    {
        
    }
    
    public Seguidor(String id, String usuarioSeguidor, String usuarioSeguido, LocalDate dataSeguido)
    {
        this.id = id;
        this.usuarioSeguidor = usuarioSeguidor;
        this.usuarioSeguido = usuarioSeguido;
        this.dataSeguido = dataSeguido;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUsuarioSeguidor()
    {
        return usuarioSeguidor;
    }

    public void setUsuarioSeguidor(String usuarioSeguidor)
    {
        this.usuarioSeguidor = usuarioSeguidor;
    }

    public String getUsuarioSeguido()
    {
        return usuarioSeguido;
    }

    public void setUsuarioSeguido(String usuarioSeguido)
    {
        this.usuarioSeguido = usuarioSeguido;
    }

    public LocalDate getDataSeguido()
    {
        return dataSeguido;
    }

    public void setDataSeguido(LocalDate dataSeguido)
    {
        this.dataSeguido = dataSeguido;
    }
}