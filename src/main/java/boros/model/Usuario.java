package boros.model;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String password;
    private String username;
    private int estatus;
    private Date fechaRegistro;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="UsuarioPerfil", joinColumns = @JoinColumn(name = "idUsuario"),
     inverseJoinColumns = @JoinColumn(name="idPerfil"))
    private List<Perfil> perfiles;
    
    public void agregar(Perfil perfil){
        if(perfiles == null){
            perfiles = new LinkedList<>();
        }
        perfiles.add(perfil);
    }

    @Override
    public String toString() {
        return "Usuarios [email=" + email + ", estatus=" + estatus + ", fechaRegistro=" + fechaRegistro + ", id=" + id
                + ", nombre=" + nombre + ", password=" + password + ", username=" + username + "]";
    }

    

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
