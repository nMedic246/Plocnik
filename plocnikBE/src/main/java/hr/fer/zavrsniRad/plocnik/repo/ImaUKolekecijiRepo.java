package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.ImaUKolekciji;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImaUKolekecijiRepo extends JpaRepository<ImaUKolekciji,Long> {
    List<ImaUKolekciji> getAllByIdKorisnik(Korisnik korisnik);
    List<ImaUKolekciji> getAllByIdVinylPloca(VinylPloca ploca);
    int countByIdVinylPloca(VinylPloca vinylPloca);
    Integer countByIdKorisnik(Korisnik idKorisnik);
}
