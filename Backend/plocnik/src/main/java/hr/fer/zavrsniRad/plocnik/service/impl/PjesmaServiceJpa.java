package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.domain.Pjesma;
import hr.fer.zavrsniRad.plocnik.repo.PjesmaRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.PjesmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PjesmaServiceJpa  implements PjesmaService {
    @Autowired
    private PjesmaRepo pjesmaRepo;

    @Override
    public Pjesma fetch(Long idPjesma) {
        return findById(idPjesma).orElseThrow(
                () -> new EntityMissingException(Pjesma.class)
        );
    }

    @Override
    public Optional<Pjesma> findById(long id) {
        return pjesmaRepo.findById(id);
    }
}
