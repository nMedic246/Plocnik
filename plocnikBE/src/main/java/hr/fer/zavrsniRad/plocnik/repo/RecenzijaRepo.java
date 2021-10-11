package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.Recenzija;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecenzijaRepo extends JpaRepository<Recenzija,Long> {
    List<Recenzija> findAllByIdVinylPlocaOrderByDatumPostavljanjaDesc(VinylPloca vinylPloca);
}
