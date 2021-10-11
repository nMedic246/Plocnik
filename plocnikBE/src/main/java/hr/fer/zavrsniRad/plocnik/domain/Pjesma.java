package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.Duration;
import java.util.Set;

@Entity
@Table(name = "pjesma")
public class Pjesma {
    @Id
    @GeneratedValue
    @Column(name = "idpjesma")
    private Long idPjesma;

    @NotNull
    @Column(name = "nazivpjesma")
    private String nazivPjesma;

    @Column(name = "trajanjepjesma")
    private Time trajanjePjesma;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idizvodac")
    private Izvodac idIzvodac;

    @OneToMany(mappedBy ="idPjesma")
    private Set<Sadrzi> sadrzi;

    @OneToMany(mappedBy ="idPjesma")
    private Set<Producira> producira;

    public Pjesma(Long idPjesma,@NotNull String nazivPjesma, Time trajanjePjesma,@NotNull Izvodac idIzvodac) {
        this.idPjesma = idPjesma;
        this.nazivPjesma = nazivPjesma;
        this.trajanjePjesma = trajanjePjesma;
        this.idIzvodac = idIzvodac;
    }

    public Pjesma() {
    }

    public Long getIdPjesma() {
        return idPjesma;
    }

    public void setIdPjesma(Long idPjesma) {
        this.idPjesma = idPjesma;
    }

    public String getNazivPjesma() {
        return nazivPjesma;
    }

    public void setNazivPjesma(String nazivPjesma) {
        this.nazivPjesma = nazivPjesma;
    }

    public Time getTrajanjePjesma() {
        return trajanjePjesma;
    }

    public void setTrajanjePjesma(Time trajanjePjesma) {
        this.trajanjePjesma = trajanjePjesma;
    }

    public Izvodac getIdIzvodac() {
        return idIzvodac;
    }

    public void setIdIzvodac(Izvodac idIzvodac) {
        this.idIzvodac = idIzvodac;
    }
}
