package hr.fer.zavrsniRad.plocnik.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "glazbenik")
public class Glazbenik {
    @Id
    @GeneratedValue
    @Column(name = "idglazbenik")
    private Long idGlazbenik;

    @OneToOne
    @NotNull
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name = "idizvodac")
    private Izvodac izvodac;


    @Column(name = "datumrod")
    private java.sql.Date datumRod;

    @Column(name = "datumsmrti")
    private java.sql.Date datumSmrti;

    public Glazbenik(Long idGlazbenik,@NotNull Izvodac izvodac, Date datumRod, Date datumSmrti) {
        this.idGlazbenik = idGlazbenik;
        this.izvodac = izvodac;
        this.datumRod = datumRod;
        this.datumSmrti = datumSmrti;
    }

    public Glazbenik(@NotNull Izvodac izvodac, Date datumRod, Date datumSmrti) {
        this.izvodac = izvodac;
        this.datumRod = datumRod;
        this.datumSmrti = datumSmrti;
    }

    public Glazbenik() {
    }

    public Long getIdGlazbenik() {
        return this.idGlazbenik;
    }

    public void setIdGlazbenik(Long idGlazbenik) {
        this.idGlazbenik = idGlazbenik;
    }

    public Izvodac getIzvodac() {
        return this.izvodac;
    }

    public void setIzvodac(Izvodac izvodac) {
        this.izvodac = izvodac;
    }

    public java.sql.Date getDatumrod() {
        return this.datumRod;
    }

    public void setDatumrod(java.sql.Date datumRod) {
        this.datumRod = datumRod;
    }

    public java.sql.Date getDatumSmrti() {
        return this.datumSmrti;
    }

    public void setDatumsmrti(java.sql.Date datumSmrti) {
        this.datumSmrti = datumSmrti;
    }
}
