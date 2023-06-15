package deivis.desafio1.postagem;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.data.annotation.Id;

public class Postagem
{
    @Id
    private String id;
    private String texto;
    private LocalDate postagemData;
    private LocalTime postagemHora;
    private String usuario;
    private String idRepostagem;
    private String tipo;
    
    Postagem()
    {
        
    }

    public Postagem(String id, String texto, LocalDate postagemData, LocalTime postagemHora, String usuario, String idRepostagem, String tipo)
    {
        this.id = id;
        this.texto = texto;
        this.postagemData = postagemData;
        this.postagemHora = postagemHora;
        this.usuario = usuario;
        this.idRepostagem = idRepostagem;
        this.tipo = tipo;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return the texto
     */
    public String getTexto()
    {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    /**
     * @return the data_hora_postagem
     */
    public LocalDate getPostagemData()
    {
        return postagemData;
    }

    /**
     * @param postagemData the data_hora_postagem to set
     */
    public void setPostagemData(LocalDate postagemData)
    {
        this.postagemData = postagemData;
    }

    public LocalTime getPostagemHora()
    {
        return postagemHora;
    }

    public void setPostagemHora(LocalTime postagemHora)
    {
        this.postagemHora = postagemHora;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getIdRepostagem()
    {
        return idRepostagem;
    }

    public void setIdRepostagem(String idRepostagem)
    {
        this.idRepostagem = idRepostagem;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public String toJson()
    {
        try
        {
//            Postagem p = (Postagem) this.clone();
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.writeValueAsString(p);
            return "{" + '"' + "id" + '"' + ':' + '"' + getId().trim() + '"' + ','
                    + '"' + "texto" + '"' + ':' + '"' + getTexto().trim() + '"' + ','
                    + '"' + "postagemData" + '"' + ':' + '"' + getPostagemData().format(DateTimeFormatter.ISO_DATE) + '"' + ','
                    + '"' + "postagemHora" + '"' + ':' + '"' + getPostagemHora().format(DateTimeFormatter.ISO_TIME) + '"' + ','
                    + '"' + "usuario" + '"' + ':' + '"' + getUsuario() + '"' + ','
                    + '"' + "idRepostagem" + '"' + ':' + '"' + getIdRepostagem() + '"' + ','
                    + '"' + "tipo" + '"' + ':' + '"' + getTipo() + '"' + '}';
        }
        catch (Exception ex)
        {
            return "erro: " + ex.getMessage();
        }
    }
    
    public String gerarId()
    {
        String idGerado = this.getUsuario().trim() + '_';
        idGerado += String.valueOf(this.getPostagemData().getYear());
        idGerado += String.valueOf(this.getPostagemData().getMonthValue());
        idGerado += String.valueOf(this.getPostagemData().getDayOfMonth());
        idGerado += String.valueOf(this.getPostagemHora().getHour());
        idGerado += String.valueOf(this.getPostagemHora().getMinute());
        idGerado += String.valueOf(this.getPostagemHora().getSecond());
        idGerado += String.valueOf(this.getPostagemHora().getNano());
        
        return idGerado.trim();
    }
}
