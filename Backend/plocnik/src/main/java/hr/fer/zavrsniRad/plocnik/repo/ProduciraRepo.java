package hr.fer.zavrsniRad.plocnik.repo;
import hr.fer.zavrsniRad.plocnik.domain.Pjesma;
import hr.fer.zavrsniRad.plocnik.domain.Producira;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProduciraRepo extends JpaRepository<Producira,Long> {
    List<Producira> getAllByIdPjesma(Pjesma pjesma);
}
