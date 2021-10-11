package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Korisnik;

import java.util.List;
import java.util.Optional;

public interface KorisnikService {
    List<Korisnik> listAll();

    Korisnik createKorisnik(Korisnik korisnik);

    Optional<Korisnik> findById(long id);

    Korisnik findByKorisnickoIme(String korisnickoIme);

    Korisnik fetch(long idKorisnik);

    long countAll();

    List<Korisnik> filterByUsername(String korisnickoIme);

    Korisnik updateKorisnik(Korisnik noviKorisnik);

    Korisnik deleteKorisnik(Long idKorisnik);
}
