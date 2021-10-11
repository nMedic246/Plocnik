package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "vlasnik")
public class Vlasnik {
    @Id
    @GeneratedValue
    @Column(name = "idvlasnik")
    private Long idVlasnik;

    @NotNull
    @Column(name = "nazivvlasnik")
    private String nazivVlasnik;

    @OneToMany(mappedBy = "idVlasnik")
    private Set<IzdavackaKuca> izdavackeKuce;

    public Vlasnik(Long idVlasnik,@NotNull String nazivVlasnik) {
        this.idVlasnik = idVlasnik;
        this.nazivVlasnik = nazivVlasnik;
    }

    public Vlasnik() {
    }

    public Long getIdVlasnik() {
        return idVlasnik;
    }

    public void setIdVlasnik(Long idVlasnik) {
        this.idVlasnik = idVlasnik;
    }

    public String getNazivVlasnik() {
        return nazivVlasnik;
    }

    public void setNazivVlasnik(String nazivVlasnik) {
        this.nazivVlasnik = nazivVlasnik;
    }
}
