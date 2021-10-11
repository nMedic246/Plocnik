package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Pjesma;
import hr.fer.zavrsniRad.plocnik.domain.Sadrzi;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SadrziService {


    List<Sadrzi> getAllByIdVinylPloca(VinylPloca vinylPloca);
}
