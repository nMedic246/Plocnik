package hr.fer.zavrsniRad.plocnik.domain.id;

import java.io.Serializable;
import java.util.Objects;

public class produciraId implements Serializable {
    private Long idPjesma;
    private Long idProducent;

    public produciraId(Long idPjesma, Long idProducent) {
        this.idPjesma = idPjesma;
        this.idProducent = idProducent;
    }

    public produciraId() {
    }

    public Long getIdPjesma() {
        return idPjesma;
    }

    public void setIdPjesma(Long idPjesma) {
        this.idPjesma = idPjesma;
    }

    public Long getIdProducent() {
        return idProducent;
    }

    public void setIdProducent(Long idProducent) {
        this.idProducent = idProducent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPjesma, idProducent);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        produciraId that = (produciraId) obj;
        return Objects.equals(idPjesma, that.idPjesma) &&
                Objects.equals(idProducent, that.idProducent);
    }
}
