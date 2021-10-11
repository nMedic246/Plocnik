package hr.fer.zavrsniRad.plocnik.domain;

import hr.fer.zavrsniRad.plocnik.domain.id.jeDioId;

import javax.persistence.*;

@Entity
@IdClass(jeDioId.class)
@Table(name = "jedio")
public class JeDio {

    @Id
    @ManyToOne
   // @Column(name = "idglazbenik")
    private Glazbenik idGlazbenik;

    @Id
    @ManyToOne
    //@Column(name = "idgrupa")
    private Grupa idGrupa;

    public JeDio(Glazbenik idGlazbenik, Grupa idGrupa) {
        this.idGlazbenik = idGlazbenik;
        this.idGrupa = idGrupa;
    }

    public JeDio() {
    }

    public Glazbenik getIdGlazbenik() {
        return idGlazbenik;
    }

    public void setIdGlazbenik(Glazbenik idGlazbenik) {
        this.idGlazbenik = idGlazbenik;
    }

    public Grupa getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(Grupa idGrupa) {
        this.idGrupa = idGrupa;
    }
}
