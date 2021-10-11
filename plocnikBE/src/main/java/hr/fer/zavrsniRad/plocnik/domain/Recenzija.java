package hr.fer.zavrsniRad.plocnik.domain;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "recenzija")
public class Recenzija {
    @Id
    @Column(name = "idrecenzija")
    @GeneratedValue
    private Long idRecenzija;

    @Column(name = "tekst")
    private String tekst;

    @NotNull
    @Column(name = "datumpostavljanja")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date datumPostavljanja;

    @NotNull
    @Column(name = "ocjena")
    @Min(value=1)
    @Max(value=5)
    private Long ocjena;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idkorisnik")
    private Korisnik idKorisnik;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idvinylploca")
    private VinylPloca idVinylPloca;

    public Recenzija(Long idRecenzija, String tekst,@NotNull Long ocjena,@NotNull Korisnik idkorisnik,@NotNull VinylPloca idvinylploca) {
        this.idRecenzija = idRecenzija;
        this.tekst = tekst;
        this.datumPostavljanja = Calendar.getInstance(TimeZone.getTimeZone("CEST")).getTime();
        this.ocjena = ocjena;
        this.idKorisnik = idkorisnik;
        this.idVinylPloca = idvinylploca;
    }

    public Recenzija(String tekst, @NotNull @Min(value = 1) @Max(value = 5) Long ocjena, @NotNull Korisnik idKorisnik, @NotNull VinylPloca idVinylPloca) {
        this.tekst = tekst;
        this.datumPostavljanja = Calendar.getInstance().getTime();
        this.ocjena = ocjena;
        this.idKorisnik = idKorisnik;
        this.idVinylPloca = idVinylPloca;
    }

    public Recenzija() {
    }

    public Long getIdRecenzija() {
        return idRecenzija;
    }

    public void setIdRecenzija(Long idRecenzija) {
        this.idRecenzija = idRecenzija;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatumPostavljanja() {
        return datumPostavljanja;
    }

    public void setDatumPostavljanja() {
        this.datumPostavljanja = Calendar.getInstance(TimeZone.getTimeZone("CEST")).getTime();
    }

    public Long getOcjena() {
        return ocjena;
    }

    public void setOcjena(Long ocjena) {
        this.ocjena = ocjena;
    }

    public Korisnik getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Korisnik idkorisnik) {
        this.idKorisnik = idkorisnik;
    }

    public VinylPloca getIdVinylPloca() {
        return idVinylPloca;
    }

    public void setIdVinylPloca(VinylPloca idvinylploca) {
        this.idVinylPloca = idvinylploca;
    }
}
