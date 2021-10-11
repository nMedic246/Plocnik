package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.ImaUKolekciji;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.domain.Zeli;

import java.util.List;
import java.util.Optional;

public interface ZeliService {
    void save(VinylPloca vinylPloca, Korisnik korisnik);
    void remove(VinylPloca vinylPloca ,Korisnik korisnik);

    List<Zeli> getAllByIdVinylPloca(VinylPloca ploca);
    List<Zeli> getAllByIdKorisnik(Korisnik korisnik);
    List<Optional<VinylPloca>> getMostWantedRecords();
}
