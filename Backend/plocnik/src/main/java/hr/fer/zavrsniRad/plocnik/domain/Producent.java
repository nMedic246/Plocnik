package hr.fer.zavrsniRad.plocnik.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "producent")
public class Producent {
    @Id
    @GeneratedValue
    @Column(name = "idproducent")
    private Long idProducent;

    @NotNull
    @Column(name = "nazivproducent")
    private String nazivProducent;

    @Column(name = "pocdjelovanjaprod")
    private java.sql.Date pocDjelovanjaProd;

    @Column(name = "krajdjelovanjaprod")
    private java.sql.Date krajDjelovanjaProd;

    @OneToMany(mappedBy ="idProducent")
    private Set<Producira> producira;

    public Producent(Long idProducent,@NotNull String nazivProducent, Date pocDjelovanjaProd, Date krajDjelovanjaProd) {
        this.idProducent = idProducent;
        this.nazivProducent = nazivProducent;
        this.pocDjelovanjaProd = pocDjelovanjaProd;
        this.krajDjelovanjaProd = krajDjelovanjaProd;
    }

    public Producent() {
    }

    public Long getIdProducent() {
        return idProducent;
    }

    public void setIdProducent(Long idProducent) {
        this.idProducent = idProducent;
    }

    public String getNazivProducent() {
        return nazivProducent;
    }

    public void setNazivProducent(String nazivProducent) {
        this.nazivProducent = nazivProducent;
    }

    public Date getPocDjelovanjaProd() {
        return pocDjelovanjaProd;
    }

    public void setPocDjelovanjaProd(Date pocDjelovanjaProd) {
        this.pocDjelovanjaProd = pocDjelovanjaProd;
    }

    public Date getKrajDjelovanjaProd() {
        return krajDjelovanjaProd;
    }

    public void setKrajDjelovanjaProd(Date krajDjelovanjaProd) {
        this.krajDjelovanjaProd = krajDjelovanjaProd;
    }
}
