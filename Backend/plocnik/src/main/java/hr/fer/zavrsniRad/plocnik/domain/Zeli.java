package hr.fer.zavrsniRad.plocnik.domain;

import hr.fer.zavrsniRad.plocnik.domain.id.ZeliId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(ZeliId.class)
@Table(name = "zeli")
public class Zeli {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "idvinylploca")
    private VinylPloca idVinylPloca;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "idkorisnik")
    private Korisnik idKorisnik;

    public Zeli(@NotNull VinylPloca idVinylPloca,@NotNull Korisnik idKorisnik) {
        this.idVinylPloca = idVinylPloca;
        this.idKorisnik = idKorisnik;
    }

    public Zeli() {
    }

    public VinylPloca getIdVinylPloca() {
        return idVinylPloca;
    }

    public void setIdVinylPloca(VinylPloca idVinylPloca) {
        this.idVinylPloca = idVinylPloca;
    }

    public Korisnik getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Korisnik idKorisnik) {
        this.idKorisnik = idKorisnik;
    }
}
