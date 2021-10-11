package hr.fer.zavrsniRad.plocnik.domain.id;


import java.io.Serializable;
import java.util.Objects;

public class JeZanraId implements Serializable {
    private Long idZanr;
    private Long idVinylPloca;

    public JeZanraId(Long idZanr, Long idVinylPloca) {
        this.idZanr = idZanr;
        this.idVinylPloca = idVinylPloca;
    }

    public JeZanraId() {
    }

    public Long getIdZanr() {
        return idZanr;
    }

    public void setIdZanr(Long idZanr) {
        this.idZanr = idZanr;
    }

    public Long getIdVinylPloca() {
        return idVinylPloca;
    }

    public void setIdVinylPloca(Long idVinylPloca) {
        this.idVinylPloca = idVinylPloca;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZanr, idVinylPloca);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JeZanraId that = (JeZanraId) obj;
        return Objects.equals(idZanr, that.idZanr) &&
                Objects.equals(idVinylPloca, that.idVinylPloca);
    }
}
