package hr.fer.zavrsniRad.plocnik.helpers;

public class IzdavackaKucaDTO {
    private Long izdavackaKucaId;
    private  String nazivIzdavackaKuca;
    private String infoIzdavackaKuca;
    private long idMjesto;
    private long idVlasnik;

    public IzdavackaKucaDTO(Long izdavackaKucaId, String nazivIzdavackaKuca, String infoIzdavackaKuca, long idMjesto, long idVlasnik) {
        this.izdavackaKucaId = izdavackaKucaId;
        this.nazivIzdavackaKuca = nazivIzdavackaKuca;
        this.infoIzdavackaKuca = infoIzdavackaKuca;
        this.idMjesto = idMjesto;
        this.idVlasnik = idVlasnik;
    }

    public Long getIzdavackaKucaId() {
        return izdavackaKucaId;
    }

    public void setIzdavackaKucaId(Long izdavackaKucaId) {
        this.izdavackaKucaId = izdavackaKucaId;
    }

    public String getNazivIzdavackaKuca() {
        return nazivIzdavackaKuca;
    }

    public void setNazivIzdavackaKuca(String nazivIzdavackaKuca) {
        this.nazivIzdavackaKuca = nazivIzdavackaKuca;
    }

    public String getInfoIzdavackaKuca() {
        return infoIzdavackaKuca;
    }

    public void setInfoIzdavackaKuca(String infoIzdavackaKuca) {
        this.infoIzdavackaKuca = infoIzdavackaKuca;
    }

    public long getIdMjesto() {
        return idMjesto;
    }

    public void setIdMjesto(long idMjesto) {
        this.idMjesto = idMjesto;
    }

    public long getIdVlasnik() {
        return idVlasnik;
    }

    public void setIdVlasnik(long idVlasnik) {
        this.idVlasnik = idVlasnik;
    }
}
