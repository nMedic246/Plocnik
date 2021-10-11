package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.JeZanra;
import hr.fer.zavrsniRad.plocnik.domain.Sadrzi;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.domain.Zanr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JeZanraRepo extends JpaRepository<JeZanra,Long> {
    List<JeZanra> getAllByIdVinylPloca(VinylPloca vinylPloca);
    List<JeZanra> getAllByIdZanr(Zanr zanr);
}
