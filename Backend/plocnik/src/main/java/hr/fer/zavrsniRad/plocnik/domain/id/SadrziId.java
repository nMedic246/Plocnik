package hr.fer.zavrsniRad.plocnik.domain.id;

import java.io.Serializable;
import java.util.Objects;

public class SadrziId implements Serializable {

    private Long idPjesma;
    private Long idVinylPloca;

    public SadrziId(Long idPjesma, Long idVinylPloca) {
        this.idPjesma = idPjesma;
        this.idVinylPloca = idVinylPloca;
    }

    public SadrziId() {
    }

    public Long getIdPjesma() {
        return idPjesma;
    }

    public void setIdPjesma(Long idPjesma) {
        this.idPjesma = idPjesma;
    }

    public Long getIdVinylPloca() {
        return idVinylPloca;
    }

    public void setIdVinylPloca(Long idVinylPloca) {
        this.idVinylPloca = idVinylPloca;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPjesma, idVinylPloca);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SadrziId that = (SadrziId) obj;
        return Objects.equals(idPjesma, that.idPjesma) &&
                Objects.equals(idVinylPloca, that.idVinylPloca);
    }
}
