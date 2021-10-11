package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IzvodacService {
    List<Izvodac> listAll();

    Optional<Izvodac> findById(long id);

    Izvodac fetch(long idIzvodac);

    Izvodac createIzvodac(Izvodac izvodac);

    Izvodac deleteIzvodac(Long idIzvodac);

    List<Izvodac> filterIzvodac(String filter);
}
