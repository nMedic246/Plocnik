package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;

@Entity
@Table(name = "slika")
public class Slika {
    @Id
    @GeneratedValue
    @Column(name = "idslika")
    private Long idSlika;

    @Column(name = "slika")
    private String slika;

    @OneToOne
    @JoinColumn(name = "idkorisnik")
    private Korisnik korisnik;

    @OneToOne
    @JoinColumn(name = "idvinylploca")
    private VinylPloca vinylPloca;

    @OneToOne
    @JoinColumn(name = "idizvodac")
    private Izvodac izvodac;

    @OneToOne
    @JoinColumn(name = "idizdavackakuca")
    private IzdavackaKuca izdavackaKuca;

    public Slika(Long idSlika, String slika, Korisnik korisnik, VinylPloca vinylploca, Izvodac izvodac, IzdavackaKuca izdavackakuca) {
        this.idSlika = idSlika;
        this.slika = slika;
        this.korisnik = korisnik;
        this.vinylPloca = vinylploca;
        this.izvodac = izvodac;
        this.izdavackaKuca = izdavackakuca;
    }

    public Slika() {
    }

    public Long getIdSlika() {
        return idSlika;
    }

    public void setIdSlika(Long idSlika) {
        this.idSlika = idSlika;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public VinylPloca getVinylploca() {
        return vinylPloca;
    }

    public void setVinylploca(VinylPloca vinylploca) {
        this.vinylPloca = vinylploca;
    }

    public Izvodac getIzvodac() {
        return izvodac;
    }

    public void setIzvodac(Izvodac izvodac) {
        this.izvodac = izvodac;
    }

    public IzdavackaKuca getIzdavackakuca() {
        return izdavackaKuca;
    }

    public void setIzdavackakuca(IzdavackaKuca izdavackakuca) {
        this.izdavackaKuca = izdavackakuca;
    }
}
