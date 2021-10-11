package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.Zanr;

import java.util.List;
import java.util.Optional;

public interface ZanrService {
    List<Zanr> findAll();
    Zanr findByNazivZanr(String nazivZanr);
    Optional<Zanr> findById(long id);
    Zanr fetch(Long idZanr);
}
