package hr.fer.zavrsniRad.plocnik.domain;

import hr.fer.zavrsniRad.plocnik.domain.id.JeZanraId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(JeZanraId.class)
@Table(name = "jezanra")
public class JeZanra {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "idzanr")
    private Zanr idZanr;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "idvinylploca")
    private VinylPloca idVinylPloca;

    public JeZanra(@NotNull Zanr idZanr,@NotNull VinylPloca idVinylPloca) {
        this.idZanr = idZanr;
        this.idVinylPloca = idVinylPloca;
    }

    public JeZanra() {
    }

    public Zanr getIdZanr() {
        return idZanr;
    }

    public void setIdZanr(Zanr idZanr) {
        this.idZanr = idZanr;
    }

    public VinylPloca getIdVinylPloca() {
        return idVinylPloca;
    }

    public void setIdVinylPloca(VinylPloca idVinylPloca) {
        this.idVinylPloca = idVinylPloca;
    }
}
