package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "vrstaploce")
public class VrstaPloce{
    @Id
    @GeneratedValue
    @Column(name = "idvrsta")
    private Long idVrsta;

    @NotNull
    @Column(name = "nazivvrsta",unique = true)
    private String nazivVrsta;

    @OneToMany(mappedBy = "idVrsta")
    private Set<VinylPloca> vinylPloce;

    public VrstaPloce(Long idVrsta,@NotNull String nazivVrsta) {
        this.idVrsta = idVrsta;
        this.nazivVrsta = nazivVrsta;
    }

    public VrstaPloce() {
    }

    public Long getIdVrsta() {
        return idVrsta;
    }

    public void setIdVrsta(Long idVrsta) {
        this.idVrsta = idVrsta;
    }

    public String getNazivVrsta() {
        return nazivVrsta;
    }

    public void setNazivVrsta(String nazivVrsta) {
        this.nazivVrsta = nazivVrsta;
    }
}
