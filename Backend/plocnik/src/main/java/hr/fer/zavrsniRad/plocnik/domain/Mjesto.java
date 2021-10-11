package hr.fer.zavrsniRad.plocnik.domain;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "mjesto")
public class Mjesto {
    @Id
    @GeneratedValue
    @Column(name = "idmjesto")
    private Long idMjesto;

    @Column(name = "pbr",unique = true)
    @NotNull
    private String pbr;

    @NotNull
    @Column(name = "nazivmjesto")
    private String nazivMjesto;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "iddrzava")
    private Drzava idDrzava;

    @OneToMany(mappedBy = "idMjesto")
    private Set<IzdavackaKuca> izdavackeKuce;

    public Mjesto(Long idMjesto,@NotNull String pbr,@NotNull String nazivMjesto,@NotNull Drzava idDrzava) {
        this.idMjesto = idMjesto;
        this.pbr = pbr;
        this.nazivMjesto = nazivMjesto;
        this.idDrzava = idDrzava;
    }

    public Mjesto() {
    }

    public Long getIdMjesto() {
        return idMjesto;
    }

    public void setIdMjesto(Long idMjesto) {
        this.idMjesto = idMjesto;
    }

    public String getPbr() {
        return pbr;
    }

    public void setPbr(String pbr) {
        this.pbr = pbr;
    }

    public String getNazivMjesto() {
        return nazivMjesto;
    }

    public void setNazivMjesto(String nazivMjesto) {
        this.nazivMjesto = nazivMjesto;
    }

    public Drzava getIdDrzava() {
        return idDrzava;
    }

    public void setIdDrzava(Drzava idDrzava) {
        this.idDrzava = idDrzava;
    }
}
