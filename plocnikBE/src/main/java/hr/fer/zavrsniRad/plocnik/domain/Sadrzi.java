package hr.fer.zavrsniRad.plocnik.domain;

import hr.fer.zavrsniRad.plocnik.domain.id.SadrziId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sadrzi")
@IdClass(SadrziId.class)
public class Sadrzi {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "idpjesma")
    private Pjesma idPjesma;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "idvinylploca")
    private VinylPloca idVinylPloca;

    @Column(name = "rednibr")
    private Long rednibr;

    public Sadrzi(@NotNull Pjesma idPjesma,@NotNull VinylPloca idVinylPloca, Long rednibr) {
        this.idPjesma = idPjesma;
        this.idVinylPloca = idVinylPloca;
        this.rednibr = rednibr;
    }

    public Sadrzi() {
    }

    public Pjesma getIdPjesma() {
        return idPjesma;
    }

    public void setIdPjesma(Pjesma idPjesma) {
        this.idPjesma = idPjesma;
    }

    public VinylPloca getIdVinylPloca() {
        return idVinylPloca;
    }

    public void setIdVinylPloca(VinylPloca idVinylPloca) {
        this.idVinylPloca = idVinylPloca;
    }

    public Long getRednibr() {
        return rednibr;
    }

    public void setRednibr(Long rednibr) {
        this.rednibr = rednibr;
    }
}
