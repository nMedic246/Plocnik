package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.Sadrzi;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SadrziRepo extends JpaRepository<Sadrzi,Long> {

    List<Sadrzi> getAllByIdVinylPloca(VinylPloca vinylPloca);
}
