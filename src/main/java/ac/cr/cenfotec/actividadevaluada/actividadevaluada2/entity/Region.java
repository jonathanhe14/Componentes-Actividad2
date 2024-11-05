package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;

    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL)
    private List<Sede> sedes;

    public Region() {
    }

    public Region(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Sede> getSedes() {
        return sedes;
    }

    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }
}
