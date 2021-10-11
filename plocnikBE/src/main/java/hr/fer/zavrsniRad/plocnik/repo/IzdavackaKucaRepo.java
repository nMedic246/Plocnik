package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.IzdavackaKuca;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IzdavackaKucaRepo  extends JpaRepository<IzdavackaKuca,Long> {
    List<IzdavackaKuca> findAllByOrderByNazivIzdavackaKucaAsc();
    @Query(value="SELECT * FROM izdavackaKuca where LOWER(nazivIzdavackaKuca) LIKE '%' || LOWER(?1) || '%'",
            nativeQuery = true)
    List<IzdavackaKuca> filterIzdavackaKucaByNazivIzdavackaKuca(String filter);
}
