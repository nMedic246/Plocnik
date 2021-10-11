package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "izdavackakuca")
public class IzdavackaKuca {
    @Id
    @GeneratedValue
    @Column(name = "idizdavackakuca")
    private Long idIzdavackaKuca;

    @NotNull
    @Column(name = "nazivizdavackakuca")
    private String nazivIzdavackaKuca;

    @Column(name = "infoizdavackakuca")
    private String infoIzdavackaKuca;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idmjesto")
    private Mjesto idMjesto;

    @ManyToOne
    @JoinColumn(name = "idvlasnik")
    private Vlasnik idVlasnik;

    @OneToMany(mappedBy = "idIzdavackaKuca")
    private Set<VinylPloca> vinylPloce;

    @OneToOne(mappedBy = "izdavackaKuca")
    private Slika slika;

    public IzdavackaKuca(Long idIzdavackaKuca,@NotNull String nazivIzdavackaKuca, String infoIzdavackaKuca,@NotNull Mjesto idMjesto, Vlasnik idVlasnik) {
        this.idIzdavackaKuca = idIzdavackaKuca;
        this.nazivIzdavackaKuca = nazivIzdavackaKuca;
        this.infoIzdavackaKuca = infoIzdavackaKuca;
        this.idMjesto = idMjesto;
        this.idVlasnik = idVlasnik;
    }

    public IzdavackaKuca(@NotNull String nazivIzdavackaKuca, String infoIzdavackaKuca, @NotNull Mjesto idMjesto, Vlasnik idVlasnik) {
        this.nazivIzdavackaKuca = nazivIzdavackaKuca;
        this.infoIzdavackaKuca = infoIzdavackaKuca;
        this.idMjesto = idMjesto;
        this.idVlasnik = idVlasnik;
    }

    public IzdavackaKuca() {
    }

    public Long getIdIzdavackaKuca() {
        return idIzdavackaKuca;
    }

    public void setIdIzdavackaKuca(Long idIzdavackaKuca) {
        this.idIzdavackaKuca = idIzdavackaKuca;
    }

    public String getNazivIzdavackaKuca() {
        return nazivIzdavackaKuca;
    }

    public void setNazivIzdavackaKuca(String nazivIzdavackaKuca) {
        this.nazivIzdavackaKuca = nazivIzdavackaKuca;
    }

    public String getInfoIzdavackaKuca() {
        return infoIzdavackaKuca;
    }

    public void setInfoIzdavackaKuca(String infoIzdavackaKuca) {
        this.infoIzdavackaKuca = infoIzdavackaKuca;
    }

    public Mjesto getIdMjesto() {
        return idMjesto;
    }

    public void setIdMjesto(Mjesto idMjesto) {
        this.idMjesto = idMjesto;
    }

    public Vlasnik getIdVlasnik() {
        return idVlasnik;
    }

    public void setIdVlasnik(Vlasnik idVlasnik) {
        this.idVlasnik = idVlasnik;
    }
}
