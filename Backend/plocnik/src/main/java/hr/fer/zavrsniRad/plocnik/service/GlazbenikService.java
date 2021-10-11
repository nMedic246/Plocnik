package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Glazbenik;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;

import java.util.List;
import java.util.Optional;

public interface GlazbenikService {
    List<Glazbenik> listAll();

    Optional<Glazbenik> findByIzvodacId(long id);

    Glazbenik fetch(long idGlazbenik);

    Glazbenik createGlazbenik(Glazbenik glazbenik);
}
