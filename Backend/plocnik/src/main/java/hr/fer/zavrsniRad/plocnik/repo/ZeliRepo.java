package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.ImaUKolekciji;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.domain.Zeli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


public interface ZeliRepo extends JpaRepository<Zeli,Long> {
    List<Zeli> getAllByIdKorisnik(Korisnik korisnik);
    List<Zeli> getAllByIdVinylPloca(VinylPloca ploca);
    int countByIdVinylPloca(VinylPloca vinylPloca);
}
