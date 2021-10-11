package hr.fer.zavrsniRad.plocnik.domain;

import com.sun.istack.Nullable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "grupa")
public class Grupa {

    @Id
    @GeneratedValue
    @Column(name = "idgrupa")
    private Long idGrupa;

    @OneToOne
    @NotNull
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="idizvodac")
    private Izvodac izvodac;

    @Nullable
    @Column(name = "pocdjelovanjagrupa")
    private Integer pocDjelovanjaGrupa;

    @Nullable
    @Column(name = "krajdjelovanjagrupa")
    private Integer krajDjelovanjaGrupa;


    public Grupa(Long idGrupa,@NotNull Izvodac izvodac,@Nullable Integer pocDjelovanjaGrupa,@Nullable Integer krajDjelovanjaGrupa) {
        this.idGrupa = idGrupa;
        this.izvodac = izvodac;
        this.pocDjelovanjaGrupa = pocDjelovanjaGrupa;
        this.krajDjelovanjaGrupa = krajDjelovanjaGrupa;
    }

    public Grupa(@NotNull Izvodac izvodac, Integer pocDjelovanjaGrupa, Integer krajDjelovanjaGrupa) {
        this.izvodac = izvodac;
        this.pocDjelovanjaGrupa = pocDjelovanjaGrupa;
        this.krajDjelovanjaGrupa = krajDjelovanjaGrupa;
    }

    public Grupa() {
    }

    public Long getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(Long idGrupa) {
        this.idGrupa = idGrupa;
    }

    public Izvodac getIzvodac() {
        return izvodac;
    }

    public void setIzvodac(Izvodac izvodac) {
        this.izvodac = izvodac;
    }

    public Integer getPocDjelovanjaGrupa() {
        return pocDjelovanjaGrupa;
    }

    public void setPocDjelovanjaGrupa(Integer pocDjelovanjaGrupa) {
        this.pocDjelovanjaGrupa = pocDjelovanjaGrupa;
    }

    public Integer getKrajDjelovanjaGrupa() {
        return krajDjelovanjaGrupa;
    }

    public void setKrajDjelovanjaGrupa(Integer krajDjelovanjaGrupa) {
        this.krajDjelovanjaGrupa = krajDjelovanjaGrupa;
    }
}
