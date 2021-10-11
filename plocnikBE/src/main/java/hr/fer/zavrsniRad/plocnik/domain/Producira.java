package hr.fer.zavrsniRad.plocnik.domain;

import hr.fer.zavrsniRad.plocnik.domain.id.produciraId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(produciraId.class)
@Table(name = "producira")
public class Producira {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "idpjesma")
    private Pjesma idPjesma;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "idproducent")
    private Producent idProducent;

    public Producira(Pjesma idPjesma, Producent idProducent) {
        this.idPjesma = idPjesma;
        this.idProducent = idProducent;
    }

    public Producira() {
    }

    public Pjesma getIdPjesma() {
        return idPjesma;
    }

    public void setIdPjesma(Pjesma idPjesma) {
        this.idPjesma = idPjesma;
    }

    public Producent getIdProducent() {
        return idProducent;
    }

    public void setIdProducent(Producent idProducent) {
        this.idProducent = idProducent;
    }
}
