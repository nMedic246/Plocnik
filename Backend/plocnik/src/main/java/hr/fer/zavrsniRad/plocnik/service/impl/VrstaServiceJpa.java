package hr.fer.zavrsniRad.plocnik.service.impl;


import hr.fer.zavrsniRad.plocnik.domain.VrstaPloce;
import hr.fer.zavrsniRad.plocnik.repo.VrstaPloceRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.VrstaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VrstaServiceJpa  implements VrstaService {
    @Autowired
    private VrstaPloceRepo vrstaRepo;

    @Override
    public List<VrstaPloce> listAll() {
        return vrstaRepo.findAll();
    }

    @Override
    public Optional<VrstaPloce> findById(long id) {
        return vrstaRepo.findById(id);
    }

    @Override
    public VrstaPloce fetch(long idVrsta) {
        return findById(idVrsta).orElseThrow(
                () -> new EntityMissingException(VrstaPloce.class)
        );
    }
}
