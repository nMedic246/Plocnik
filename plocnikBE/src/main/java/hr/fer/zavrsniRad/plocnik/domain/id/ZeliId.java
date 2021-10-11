package hr.fer.zavrsniRad.plocnik.domain.id;

import java.io.Serializable;
import java.util.Objects;

public class ZeliId  implements Serializable {
    private Long idKorisnik;
    private Long idVinylPloca;

    public ZeliId(Long idKorisnik, Long idVinylPloca) {
        this.idKorisnik = idKorisnik;
        this.idVinylPloca = idVinylPloca;
    }

    public ZeliId() {
    }

    public Long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public Long getIdVinylPloca() {
        return idVinylPloca;
    }

    public void setIdVinylPloca(Long idVinylPloca) {
        this.idVinylPloca = idVinylPloca;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKorisnik, idVinylPloca);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ZeliId that = (ZeliId) obj;
        return Objects.equals(idKorisnik, that.idKorisnik) &&
                Objects.equals(idVinylPloca, that.idVinylPloca);
    }
}
