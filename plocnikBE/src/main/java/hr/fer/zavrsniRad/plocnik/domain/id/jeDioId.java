package hr.fer.zavrsniRad.plocnik.domain.id;

import java.io.Serializable;
import java.util.Objects;

public class jeDioId implements Serializable {

    private Long idGrupa;
    private Long idGlazbenik;

    public jeDioId(Long idGrupa, Long idGlazbenik) {
        this.idGrupa = idGrupa;
        this.idGlazbenik = idGlazbenik;
    }

    public jeDioId() {
    }

    public Long getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(Long idGrupa) {
        this.idGrupa = idGrupa;
    }

    public Long getIdGlazbenik() {
        return idGlazbenik;
    }

    public void setIdGlazbenik(Long idGlazbenik) {
        this.idGlazbenik = idGlazbenik;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrupa, idGlazbenik);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        jeDioId that = (jeDioId) obj;
        return Objects.equals(idGrupa, that.idGrupa) &&
                Objects.equals(idGlazbenik, that.idGlazbenik);
    }
}
