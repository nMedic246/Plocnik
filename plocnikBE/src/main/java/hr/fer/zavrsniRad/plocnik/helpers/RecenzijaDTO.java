package hr.fer.zavrsniRad.plocnik.helpers;

import hr.fer.zavrsniRad.plocnik.domain.Recenzija;
import hr.fer.zavrsniRad.plocnik.repo.SlikaRepo;
import hr.fer.zavrsniRad.plocnik.service.SlikaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecenzijaDTO {

    private Long idRecenzija;
    private String korisnickoIme;
    private Long ocjena;
    private String tekst;
    private Long idKorisnik;
    private java.util.Date date;


    public RecenzijaDTO(Long idRecenzija, String korisnickoIme, Long ocjena, String tekst,Date date,Long idKorisnik) {
        this.idRecenzija = idRecenzija;
        this.korisnickoIme = korisnickoIme;
        this.ocjena = ocjena;
        this.tekst = tekst;
        this.date =  date;
        this.idKorisnik=idKorisnik;
    }

    public RecenzijaDTO() {
    }

    public Long getIdRecenzija() {
        return idRecenzija;
    }

    public void setIdRecenzija(Long idRecenzija) {
        this.idRecenzija = idRecenzija;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public Long getOcjena() {
        return ocjena;
    }

    public void setOcjena(Long ocjena) {
        this.ocjena = ocjena;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;;
    }

    public Long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public static RecenzijaDTO fromRecenzijaToRecenzijaDTO(Recenzija recenzija){
        return new RecenzijaDTO(recenzija.getIdRecenzija(),recenzija.getIdKorisnik().getKorisnickoIme(),recenzija.getOcjena(),recenzija.getTekst(),recenzija.getDatumPostavljanja(),recenzija.getIdKorisnik().getIdKorisnik());
    }

    public static List<RecenzijaDTO> fromRecenzijaListToRecenzijaDTOList(List<Recenzija> recenzijas) {
        List<RecenzijaDTO> recenzijaDTOS = new ArrayList<>();
        for (Recenzija r : recenzijas) {
            recenzijaDTOS.add(new RecenzijaDTO(r.getIdRecenzija(),r.getIdKorisnik().getKorisnickoIme(),r.getOcjena(),r.getTekst(),r.getDatumPostavljanja(),r.getIdKorisnik().getIdKorisnik()));
        }
        return recenzijaDTOS;
    }
}
