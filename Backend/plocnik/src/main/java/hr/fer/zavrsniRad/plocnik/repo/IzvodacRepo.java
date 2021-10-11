package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IzvodacRepo extends JpaRepository<Izvodac,Long> {
    Izvodac findByIdIzvodac(Long idIzvodac);
    List<Izvodac> findAllByOrderByNazivIzvodac();

    @Query(value="SELECT * FROM izvodac where LOWER(nazivizvodac) LIKE '%' || LOWER(?1) || '%'",
            nativeQuery = true)
    List<Izvodac> filterIzvodacByNazivIzvodac(String filter);
}
