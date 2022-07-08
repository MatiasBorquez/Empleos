package boros.model;

import javax.persistence.*;

@Entity
@Table(name="Perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String perfil;

    @Override
    public String toString() {
        return "Perfiles [id=" + id + ", perfil=" + perfil + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
