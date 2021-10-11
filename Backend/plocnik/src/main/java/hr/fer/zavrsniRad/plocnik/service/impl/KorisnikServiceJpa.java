package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.repo.KorisnikRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.KorisnikService;
import hr.fer.zavrsniRad.plocnik.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikServiceJpa implements KorisnikService {

    @Autowired
    private KorisnikRepo korisnikRepo;

    @Override
    public List<Korisnik> listAll() {
        return korisnikRepo.findAll();
    }

    @Override
    public Korisnik createKorisnik(Korisnik korisnik) {
        validate(korisnik);

        if(korisnik.getZaporka().length()<5 || korisnik.getZaporka().length()>30) {
            throw new RequestDeniedException("Zaporka mora biti duža od 4 znaka!");
        }
        if(korisnikRepo.countByEmail(korisnik.getEmail())>0){
            throw new RequestDeniedException("Korisnik s tim emailom već postoji!");
        }

        if(korisnikRepo.countByKorisnickoIme(korisnik.getKorisnickoIme())>0){
            throw new RequestDeniedException("Korisnik s tim korisničkim imenom već postoji!");
        }
        korisnik.setZaporka(new BCryptPasswordEncoder().encode(korisnik.getZaporka()));

        return korisnikRepo.save(korisnik);
    }

    @Override
    public Optional<Korisnik> findById(long id) {
        return korisnikRepo.findById(id);
    }

    @Override
    public Korisnik findByKorisnickoIme(String korisnickoIme) {
        return korisnikRepo.findByKorisnickoIme(korisnickoIme);
    }

    @Override
    public Korisnik fetch(long id) {
        return findById(id).orElseThrow(
                () -> new EntityMissingException(Korisnik.class)
        );
    }

    @Override
    public long countAll() {
        return korisnikRepo.count();
    }

    @Override
    public List<Korisnik> filterByUsername(String korisnickoIme) {
        if(korisnickoIme=="" || korisnickoIme==null){
            return korisnikRepo.findAll();
        }else{
            return korisnikRepo.findAllByKorisnickoIme(korisnickoIme);
        }
    }

    @Override
    public Korisnik updateKorisnik(Korisnik noviKorisnik) {
        Long idKorisnik = noviKorisnik.getIdKorisnik();


        if(noviKorisnik.getZaporka()!=null){
            if(noviKorisnik.getZaporka().length()<5 || noviKorisnik.getZaporka().length()>30) {
                throw new RequestDeniedException("Zaporka mora biti duža od 4 znaka!");
            }
        }

        if(noviKorisnik.getKorisnickoIme()!=null){
            if(korisnikRepo.existsByKorisnickoImeAndIdKorisnikNot(noviKorisnik.getKorisnickoIme(),idKorisnik)){
                throw  new RequestDeniedException("Korisnik s tim korisničkim imenom već postoji!");
            }
            korisnikRepo.updateKorisnickoIme(idKorisnik,noviKorisnik.getKorisnickoIme());
        }

        if(noviKorisnik.getZaporka()!=null){
            korisnikRepo.updateZaporka(idKorisnik,new BCryptPasswordEncoder().encode(noviKorisnik.getZaporka()));
        }
        return fetch(idKorisnik);
    }

    @Override
    public Korisnik deleteKorisnik(Long idKorisnik) {
        Korisnik korisnik = fetch(idKorisnik);
        korisnikRepo.delete(korisnik);
        return korisnik;
    }

    private void validate(Korisnik korisnik) {
        Assert.isNull(korisnik.getIdKorisnik(),"Id korisnika mora biti null!");
        Assert.notNull(korisnik, "Korisnik object must be given");
        Assert.notNull(korisnik.getKorisnickoIme(), "Username must be given");
        Assert.notNull(korisnik.getZaporka(), "Password must be given");
    }
}
