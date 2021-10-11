package hr.fer.zavrsniRad.plocnik.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "drzava")
public class Drzava {
    @Id
    @GeneratedValue
    @Column(name = "iddrzava")
    private Long idDrzava;

    @NotNull
    @Column(name = "nazivdrzava")
    private String nazivDrzava;

    @OneToMany(mappedBy = "idDrzava")
    private Set<Izvodac> izvodaci;


    public Drzava(Long idDrzava,@NotNull String nazivDrzava) {
        this.idDrzava = idDrzava;
        this.nazivDrzava = nazivDrzava;
    }

    public Drzava() {
    }

    public Long getIdDrzava() {
        return this.idDrzava;
    }

    public void setIdDrzava(Long iddrzava) {
        this.idDrzava = idDrzava;
    }

    public String getNazivDrzava() {
        return this.nazivDrzava;
    }

    public void setNazivDrzava(String nazivDrzava) {
        this.nazivDrzava = nazivDrzava;
    }
}
