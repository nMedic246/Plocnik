package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.Glazbenik;
import hr.fer.zavrsniRad.plocnik.domain.Grupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupaRepo  extends JpaRepository<Grupa,Long> {
    @Query(value="SELECT * FROM grupa  where grupa.idizvodac=?1",
            nativeQuery = true)
    Optional<Grupa> findByIzvodacId(long id);
}
