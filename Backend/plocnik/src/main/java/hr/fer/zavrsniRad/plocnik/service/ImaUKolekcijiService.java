package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.ImaUKolekciji;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.helpers.KorisnikDTO;

import java.util.List;
import java.util.Optional;

public interface ImaUKolekcijiService {
    void save(VinylPloca vinylPloca, Korisnik korisnik);
    void remove(VinylPloca vinylPloca ,Korisnik korisnik);
    List<ImaUKolekciji> getAllByIdKorisnik(Korisnik korisnik);
    List<ImaUKolekciji> getAllByIdVinylPloca(VinylPloca ploca);
    List<Optional<VinylPloca>> getMostPopularRecords();
    List<KorisnikDTO> getBiggestRecordOwners();
}
