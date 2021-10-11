package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "vinylploca")
public class VinylPloca {
    @Id
    @GeneratedValue
    @Column(name = "idvinylploca")
    private Long idVinylPloca;

    @NotNull
    @Column(name = "nazivvinylploca")
    private String nazivVinylPloca;

    @Column(name = "godinaizdanja")
    private Long godinaIzdanja;

    @Column(name = "infovinylploca")
    private String infoVinylPloca;

    @Column(name = "verzija")
    private String verzija;

    @Column(name="ukocjena")
    private Float ukOcjena;

    @ManyToOne
    @JoinColumn(name="idizdavackakuca")
    private IzdavackaKuca idIzdavackaKuca;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idvrsta")
    private VrstaPloce idVrsta;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idizvodac")
    private Izvodac idIzvodac;

    @OneToOne(mappedBy = "vinylPloca")
    private Slika slika;

    @OneToMany(mappedBy ="idVinylPloca")
    private Set<Sadrzi> sadrzi;

    @OneToMany(mappedBy ="idVinylPloca")
    private Set<ImaUKolekciji> imaUKolekciji;

    @OneToMany(mappedBy ="idVinylPloca")
    private Set<Zeli> zeli;

    @OneToMany(mappedBy = "idVinylPloca")
    Set<JeZanra> jeZanraSet;

    @OneToMany(mappedBy = "idVinylPloca")
    Set<Recenzija> recenzijas;

    public VinylPloca(Long idVinylPloca,@NotNull String nazivVinylPloca, Long godinaIzdanja, String infoVinylPloca, String verzija, IzdavackaKuca idIzdavackaKuca,@NotNull VrstaPloce idVrsta,@NotNull Izvodac idIzvodac,Float ukOcjena) {
        this.idVinylPloca = idVinylPloca;
        this.nazivVinylPloca = nazivVinylPloca;
        this.godinaIzdanja = godinaIzdanja;
        this.infoVinylPloca = infoVinylPloca;
        this.verzija = verzija;
        this.idIzdavackaKuca = idIzdavackaKuca;
        this.idVrsta = idVrsta;
        this.idIzvodac = idIzvodac;
        this.ukOcjena = ukOcjena;
    }

    public VinylPloca(@NotNull String nazivVinylPloca, Long godinaIzdanja, String infoVinylPloca, String verzija, IzdavackaKuca idIzdavackaKuca, @NotNull VrstaPloce idVrsta, @NotNull Izvodac idIzvodac) {
        this.nazivVinylPloca = nazivVinylPloca;
        this.godinaIzdanja = godinaIzdanja;
        this.infoVinylPloca = infoVinylPloca;
        this.verzija = verzija;
        this.idIzdavackaKuca = idIzdavackaKuca;
        this.idVrsta = idVrsta;
        this.idIzvodac = idIzvodac;
        this.ukOcjena=null;
    }

    public VinylPloca() {
    }

    public Long getIdVinylPloca() {
        return idVinylPloca;
    }

    public void setIdVinylPloca(Long idVinylPloca) {
        this.idVinylPloca = idVinylPloca;
    }

    public String getNazivVinylPloca() {
        return nazivVinylPloca;
    }

    public void setNazivVinylPloca(String nazivVinylPloca) {
        this.nazivVinylPloca = nazivVinylPloca;
    }

    public Long getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(Long godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public String getInfoVinylPloca() {
        return infoVinylPloca;
    }

    public void setInfoVinylPloca(String infoVinylPloca) {
        this.infoVinylPloca = infoVinylPloca;
    }

    public String getVerzija() {
        return verzija;
    }

    public void setVerzija(String verzija) {
        this.verzija = verzija;
    }

    public IzdavackaKuca getIdIzdavackaKuca() {
        return idIzdavackaKuca;
    }

    public void setIdIzdavackaKuca(IzdavackaKuca idIzdavackaKuca) {
        this.idIzdavackaKuca = idIzdavackaKuca;
    }

    public VrstaPloce getIdVrsta() {
        return idVrsta;
    }

    public void setIdVrsta(VrstaPloce idVrsta) {
        this.idVrsta = idVrsta;
    }

    public Izvodac getIdIzvodac() {
        return idIzvodac;
    }

    public void setIdIzvodac(Izvodac idIzvodac) {
        this.idIzvodac = idIzvodac;
    }

    public Float getUkOcjena() {
        return ukOcjena;
    }

    public void setUkOcjena(Float ukOcjena) {
        this.ukOcjena = ukOcjena;
    }
}
