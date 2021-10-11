package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;

import java.util.List;
import java.util.Optional;

public interface MjestoService {
    Optional<Mjesto> findById(long id);
    Mjesto fetch(long idMjesto);
    List<Mjesto> listAll();
}
