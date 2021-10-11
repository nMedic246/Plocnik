package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Drzava;
import hr.fer.zavrsniRad.plocnik.domain.Mjesto;

import java.util.List;
import java.util.Optional;

public interface DrzavaService {
    List<Drzava> listAll();
    Drzava fetch(long idDrzava);
    Optional<Drzava> findById(long id);
}
