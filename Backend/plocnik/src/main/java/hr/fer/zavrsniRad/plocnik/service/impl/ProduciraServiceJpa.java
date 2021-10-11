package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Pjesma;
import hr.fer.zavrsniRad.plocnik.domain.Producira;
import hr.fer.zavrsniRad.plocnik.repo.ProduciraRepo;
import hr.fer.zavrsniRad.plocnik.service.ProduciraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProduciraServiceJpa implements ProduciraService {
    @Autowired
    private ProduciraRepo produciraRepo;
    @Override
    public List<Producira> getAllByIdPjesma(Pjesma pjesma) {
        return produciraRepo.getAllByIdPjesma(pjesma);
    }
}
