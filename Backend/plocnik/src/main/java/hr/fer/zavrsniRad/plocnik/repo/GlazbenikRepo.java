package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.Glazbenik;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GlazbenikRepo extends JpaRepository<Glazbenik,Long> {
    @Query(value="SELECT * FROM glazbenik  where glazbenik.idizvodac=?1",
    nativeQuery = true)
    Optional<Glazbenik> findByIzvodacId(long id);
}
