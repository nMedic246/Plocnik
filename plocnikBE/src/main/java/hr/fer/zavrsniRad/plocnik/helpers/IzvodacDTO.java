package hr.fer.zavrsniRad.plocnik.helpers;

import java.sql.Date;

public class IzvodacDTO {
    private Long idIzvodac;
    private String nazivIzvodac;
    private String infoIzvodac;
    private Long idDrzava;
    private Integer pocDjelovanjaGrupa;
    private Integer krajDjelovanjaGrupa;
    private Date datumRod;
    private Date datumSmrti;
    private int jeGrupa;

    public IzvodacDTO(Long idIzvodac, String nazivIzvodac, String infoIzvodac, Long idDrzava, Integer pocDjelovanjeGrupa, Integer krajDjelovanjeGrupa, Date datRodenja, Date datSmrti,int jeGrupa) {
        this.idIzvodac = idIzvodac;
        this.nazivIzvodac = nazivIzvodac;
        this.infoIzvodac = infoIzvodac;
        this.idDrzava = idDrzava;
        this.pocDjelovanjaGrupa = pocDjelovanjeGrupa;
        this.krajDjelovanjaGrupa = krajDjelovanjeGrupa;
        this.datumRod = datRodenja;
        this.datumSmrti = datSmrti;
        this.jeGrupa =jeGrupa;
    }

    public Long getIdIzvodac() {
        return idIzvodac;
    }

    public int getJeGrupa() {
        return jeGrupa;
    }

    public void setJeGrupa(int jeGrupa) {
        this.jeGrupa = jeGrupa;
    }

    public void setIdIzvodac(Long idIzvodac) {
        this.idIzvodac = idIzvodac;
    }

    public String getNazivIzvodac() {
        return nazivIzvodac;
    }

    public void setNazivIzvodac(String nazivIzvodac) {
        this.nazivIzvodac = nazivIzvodac;
    }

    public String getInfoIzvodac() {
        return infoIzvodac;
    }

    public void setInfoIzvodac(String infoIzvodac) {
        this.infoIzvodac = infoIzvodac;
    }

    public Long getIdDrzava() {
        return idDrzava;
    }

    public void setIdDrzava(Long idDrzava) {
        this.idDrzava = idDrzava;
    }

    public Integer getPocDjelovanjaGrupa() {
        return pocDjelovanjaGrupa;
    }

    public void setPocDjelovanjaGrupa(Integer pocDjelovanjeGrupa) {
        this.pocDjelovanjaGrupa = pocDjelovanjeGrupa;
    }

    public Integer getKrajDjelovanjaGrupa() {
        return krajDjelovanjaGrupa;
    }

    public void setKrajDjelovanjaGrupa(Integer krajDjelovanjeGrupa) {
        this.krajDjelovanjaGrupa = krajDjelovanjeGrupa;
    }

    public Date getDatumRod() {
        return datumRod;
    }

    public void setDatumRod(Date datRodenja) {
        this.datumRod = datRodenja;
    }

    public Date getDatumSmrti() {
        return datumSmrti;
    }

    public void setDatumSmrti(Date datSmrti) {
        this.datumSmrti = datSmrti;
    }
}
