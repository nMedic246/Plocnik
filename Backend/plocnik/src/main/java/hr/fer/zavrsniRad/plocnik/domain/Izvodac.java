package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "izvodac")
public class Izvodac {
    @Id
    @GeneratedValue
    @Column(name = "idizvodac")
    private Long idIzvodac;

    @NotNull
    @Column(name = "nazivizvodac")
    private String nazivIzvodac;

    @Column(name = "infoizvodac")
    private String infoIzvodac;

    @Column(name="jegrupa")
    private int jegrupa;

    @ManyToOne
    @JoinColumn(name="iddrzava")
    private Drzava idDrzava;

    @OneToOne(mappedBy = "izvodac")
    private Grupa grupa;

    @OneToOne(mappedBy = "izvodac")
    private Glazbenik glazbenik;

    @OneToMany(mappedBy = "idIzvodac")
    private Set<VinylPloca> vinylPloce;

    @OneToMany(mappedBy ="idIzvodac")
    private Set<Pjesma> pjesma;

    @OneToOne(mappedBy = "izvodac")
    private Slika slika;

    public Izvodac(Long idIzvodac,@NotNull String nazivIzvodac, String infoIzvodac, Drzava idDrzava,int jegrupa) {
        this.idIzvodac = idIzvodac;
        this.nazivIzvodac = nazivIzvodac;
        this.infoIzvodac = infoIzvodac;
        this.idDrzava = idDrzava;
        this.jegrupa=jegrupa;
    }

    public Izvodac(@NotNull String nazivIzvodac, String infoIzvodac, int jegrupa, Drzava idDrzava) {
        this.nazivIzvodac = nazivIzvodac;
        this.infoIzvodac = infoIzvodac;
        this.jegrupa = jegrupa;
        this.idDrzava = idDrzava;
    }

    public Izvodac() {
    }

    public Long getIdIzvodac() {
        return idIzvodac;
    }

    public void setIdIzvodac(Long idIzvodac) {
        this.idIzvodac = idIzvodac;
    }

    public String getNazivIzvodac() {
        return nazivIzvodac;
    }

    public void setNazivIzvodac(String nazivIzvodac) {
        this.nazivIzvodac = nazivIzvodac;
    }

    public String getInfoIzvodac() {
        return infoIzvodac;
    }

    public void setInfoIzvodac(String infoIzvodac) {
        this.infoIzvodac = infoIzvodac;
    }

    public Drzava getIdDrzava() {
        return idDrzava;
    }

    public void setIdDrzava(Drzava idDrzava) {
        this.idDrzava = idDrzava;
    }

    public int getJegrupa() {
        return jegrupa;
    }

    public void setJegrupa(int jegrupa) {
        this.jegrupa = jegrupa;
    }
}
