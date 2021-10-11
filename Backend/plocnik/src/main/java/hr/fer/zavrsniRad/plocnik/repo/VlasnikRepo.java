package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.Vlasnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VlasnikRepo extends JpaRepository<Vlasnik,Long> {
}
