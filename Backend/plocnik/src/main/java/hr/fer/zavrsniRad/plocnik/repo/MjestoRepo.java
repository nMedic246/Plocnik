package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MjestoRepo  extends JpaRepository<Mjesto,Long> {
}
