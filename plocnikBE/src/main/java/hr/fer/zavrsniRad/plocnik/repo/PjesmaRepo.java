package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.Pjesma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PjesmaRepo extends JpaRepository<Pjesma,Long> {
}
