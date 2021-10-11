package hr.fer.zavrsniRad.plocnik.helpers;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class VinylPlocaDTO {
    private Long idVinylPloca;
    private String nazivVinylPloca;
    private Long godinaIzdanja;
    private String infoVinylPloca;
    private String verzija;
    private Long idIzvodac;
    private Long idIzdavackaKuca;
    private Long idVrsta;
    private Long idZanr;
    public VinylPlocaDTO(Long idVinylPloca, String nazivVinylPloca, Long godinaIzdanja, String infoVinylPloca, String verzija, Long idIzvodac, Long idIzdavackakuca, Long idVrsta,Long idZanr) {
        this.idVinylPloca = idVinylPloca;
        this.nazivVinylPloca = nazivVinylPloca;
        this.godinaIzdanja = godinaIzdanja;
        this.infoVinylPloca = infoVinylPloca;
        this.verzija = verzija;
        this.idIzvodac = idIzvodac;
        this.idIzdavackaKuca = idIzdavackakuca;
        this.idVrsta = idVrsta;
        this.idZanr =idZanr;
    }

    public VinylPlocaDTO() {
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

    public Long getIdIzvodac() {
        return idIzvodac;
    }

    public void setIdIzvodac(Long idIzvodac) {
        this.idIzvodac = idIzvodac;
    }

    public Long getIdIzdavackaKuca() {
        return idIzdavackaKuca;
    }

    public void setIdIzdavackaKuca(Long idIzdavackaKuca) {
        this.idIzdavackaKuca = idIzdavackaKuca;
    }

    public Long getIdVrsta() {
        return idVrsta;
    }

    public void setIdVrsta(Long idVrsta) {
        this.idVrsta = idVrsta;
    }

    public Long getIdZanr() {
        return idZanr;
    }

    public void setIdZanr(Long idZanr) {
        this.idZanr = idZanr;
    }
}

