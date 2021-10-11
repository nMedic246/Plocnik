package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "zanr")
public class Zanr {
    @Id
    @GeneratedValue
    @Column(name = "idzanr")
    private Long idZanr;

    @NotNull
    @Column(name = "nazivzanr",unique = true)
    private String nazivZanr;

    @OneToMany(mappedBy = "idZanr")
    Set<JeZanra> jeZanraSet;

    public Zanr(Long idZanr, String nazivZanr) {
        this.idZanr = idZanr;
        this.nazivZanr = nazivZanr;
    }

    public Zanr() {
    }

    public Long getIdZanr() {
        return idZanr;
    }

    public void setIdZanr(Long idZanr) {
        this.idZanr = idZanr;
    }

    public String getNazivZanr() {
        return nazivZanr;
    }

    public void setNazivZanr(String nazivZanr) {
        this.nazivZanr = nazivZanr;
    }
}
