package hr.fer.zavrsniRad.plocnik.helpers;

import hr.fer.zavrsniRad.plocnik.domain.Korisnik;

import java.util.Optional;

public class KorisnikDTO {
    private long idKorisnik;
    private String korisnickoIme;
    private int brojPloca;

    private String email;

    public KorisnikDTO(String korisnickoIme, String email,long idKorisnik) {
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.idKorisnik=idKorisnik;
    }

    public KorisnikDTO() {
    }

    public long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static KorisnikDTO fromKorisnikToKorisnikDTO(Korisnik korisnik){
        return new KorisnikDTO(korisnik.getKorisnickoIme(),korisnik.getEmail(),korisnik.getIdKorisnik());
    }

    public int getBrojPloca() {
        return brojPloca;
    }

    public void setBrojPloca(int brojPloca) {
        this.brojPloca = brojPloca;
    }
}
