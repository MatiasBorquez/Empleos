package boros.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Solicitudes")
public class Solicitudes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fecha;
    private String file;
    private String comentario;

    @OneToOne
    @JoinColumn(name = "idVacante")
    private Vacante vacante;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    @Override
    public String toString() {
        return "Solicitudes [comentario=" + comentario + ", fecha=" + fecha + ", file=" + file + ", id=" + id
                + ", usuario=" + usuario + ", vacante=" + vacante + "]";
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
