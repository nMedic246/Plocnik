package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "korisnik")
public class Korisnik {
    @Id
    @GeneratedValue
    @Column(name = "idkorisnik")
    private Long idKorisnik;

    @NotNull
    @Column(name = "email",unique = true)
    private String email;

    @NotNull
    @Column(name = "korisnickoime",unique = true)
    private String korisnickoIme;

    @NotNull
    @Column(name = "zaporka")
    private String zaporka;

    @OneToOne(mappedBy = "korisnik")
    private Slika slika;

    @OneToMany(mappedBy ="idKorisnik")
    private Set<ImaUKolekciji> imaUKolekciji;

    @OneToMany(mappedBy ="idKorisnik")
    private Set<Zeli> zeli;

    @OneToMany(mappedBy = "idKorisnik")
    Set<Recenzija> recenzijas;

    public Korisnik(Long idKorisnik,@NotNull String korisnickoIme,@NotNull String email,@NotNull String zaporka) {
        this.idKorisnik = idKorisnik;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.zaporka = zaporka;
    }

    public Korisnik() {
    }

    public Long getIdKorisnik() {
        return this.idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorisnickoIme() {
        return this.korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getZaporka() {
        return this.zaporka;
    }

    public void setZaporka(String zaporka) {
        this.zaporka = zaporka;
    }
}
