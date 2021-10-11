package hr.fer.zavrsniRad.plocnik.domain.id;

import java.io.Serializable;
import java.util.Objects;

public class ImaUKolekcijiId implements Serializable {
    private Long idKorisnik;
    private Long idVinylPloca;

    public ImaUKolekcijiId(Long idKorisnik, Long idVinylPloca) {
        this.idKorisnik = idKorisnik;
        this.idVinylPloca = idVinylPloca;
    }

    public ImaUKolekcijiId() {
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
        ImaUKolekcijiId that = (ImaUKolekcijiId) obj;
        return Objects.equals(idKorisnik, that.idKorisnik) &&
                Objects.equals(idVinylPloca, that.idVinylPloca);
    }
}
