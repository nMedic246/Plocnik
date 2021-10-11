package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.IzdavackaKuca;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VinylPlocaRepo extends JpaRepository<VinylPloca, Long> {

    List<VinylPloca> findAllByOrderByNazivVinylPlocaAsc();
    List<VinylPloca> findAllByOrderByUkOcjenaDesc();
    List<VinylPloca> getAllByIdIzvodac(Izvodac izvodac);
    List<VinylPloca> getAllByIdIzdavackaKuca(IzdavackaKuca izdavackaKuca);

    @Query(value="SELECT * FROM vinylploca where LOWER(nazivvinylploca) LIKE '%' || LOWER(?1) || '%'",
    nativeQuery = true)
    List<VinylPloca> filterVinylPlocaByNazivVinylPloca(String filter);
}
