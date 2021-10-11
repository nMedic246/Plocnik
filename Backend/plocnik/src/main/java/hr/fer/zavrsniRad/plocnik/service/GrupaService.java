package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Grupa;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;

import java.util.List;
import java.util.Optional;

public interface GrupaService {
    List<Grupa> listAll();

    Optional<Grupa> findByIzvodacId(long id);

    Grupa fetch(long idGrupa);

    Grupa createGrupa(Grupa grupa);
}
