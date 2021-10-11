package hr.fer.zavrsniRad.plocnik.domain;

import hr.fer.zavrsniRad.plocnik.domain.id.ImaUKolekcijiId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@IdClass(ImaUKolekcijiId.class)
@Table(name = "imaukolekciji")
public class ImaUKolekciji {
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

    @NotNull
    @Column(name = "datdodavanja")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date datDodavanja;

    public ImaUKolekciji(@NotNull VinylPloca idVinylPloca,@NotNull Korisnik idKorisnik) {
        this.idVinylPloca = idVinylPloca;
        this.idKorisnik = idKorisnik;
        this.datDodavanja = Calendar.getInstance().getTime();
    }

    public ImaUKolekciji() {
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

    public Date getDatDodavanja() {
        return datDodavanja;
    }

    public void setDatDodavanja() {
        this.datDodavanja = Calendar.getInstance().getTime();
    }
}
