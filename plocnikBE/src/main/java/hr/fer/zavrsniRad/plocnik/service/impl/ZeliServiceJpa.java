package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.ImaUKolekciji;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.domain.Zeli;
import hr.fer.zavrsniRad.plocnik.repo.VinylPlocaRepo;
import hr.fer.zavrsniRad.plocnik.repo.ZeliRepo;
import hr.fer.zavrsniRad.plocnik.service.ZeliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ZeliServiceJpa implements ZeliService {

    @Autowired
    private ZeliRepo zeliRepo;
    @Autowired
    private VinylPlocaRepo vinylPlocaRepo;

    @Override
    public void save(VinylPloca vinylPloca, Korisnik korisnik) {
        Zeli zeli = new Zeli(vinylPloca,korisnik);
        zeliRepo.save(zeli);
    }

    @Override
    public void remove(VinylPloca vinylPloca, Korisnik korisnik) {
        Zeli zeli = new Zeli(vinylPloca,korisnik);
        zeliRepo.delete(zeli);
    }

    @Override
    public List<Zeli> getAllByIdVinylPloca(VinylPloca ploca) {
        return zeliRepo.getAllByIdVinylPloca(ploca);
    }

    @Override
    public List<Zeli> getAllByIdKorisnik(Korisnik korisnik) {
        return zeliRepo.getAllByIdKorisnik(korisnik);
    }

    @Override
    public List<Optional<VinylPloca>> getMostWantedRecords() {
        Map<Long,Integer> idNumWantersMap = new HashMap<>();
        List<Zeli> zele = zeliRepo.findAll();

        for(Zeli z: zele){
            if(idNumWantersMap.containsKey(z.getIdVinylPloca().getIdVinylPloca())) {
            }else{
                idNumWantersMap.put(z.getIdVinylPloca().getIdVinylPloca(),zeliRepo.countByIdVinylPloca(z.getIdVinylPloca()));
            }
        }
        LinkedHashMap<Long,Integer> reverseS = new LinkedHashMap<>();

        idNumWantersMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x->reverseS.put(x.getKey(),x.getValue()));

        List<Optional<VinylPloca>> mostPopular = new ArrayList<>();
        int c=0;
        for(Long key : reverseS.keySet()){
            if(c<5){
                mostPopular.add( vinylPlocaRepo.findById(key));
            }else{
                break;
            }
            c++;
        }
        return mostPopular;
    }
}
