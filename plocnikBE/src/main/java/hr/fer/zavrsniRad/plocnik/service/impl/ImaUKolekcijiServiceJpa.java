package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.ImaUKolekciji;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.domain.Zeli;
import hr.fer.zavrsniRad.plocnik.helpers.KorisnikDTO;
import hr.fer.zavrsniRad.plocnik.repo.ImaUKolekecijiRepo;
import hr.fer.zavrsniRad.plocnik.repo.KorisnikRepo;
import hr.fer.zavrsniRad.plocnik.repo.VinylPlocaRepo;
import hr.fer.zavrsniRad.plocnik.service.ImaUKolekcijiService;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Handler;

@Service
public class ImaUKolekcijiServiceJpa implements ImaUKolekcijiService {

    @Autowired
    private ImaUKolekecijiRepo imaUKolekecijiRepo;
    @Autowired
    private VinylPlocaRepo vinylPlocaRepo;
    @Autowired
    private KorisnikRepo korisnikRepo;

    @Override
    public void save(VinylPloca vinylPloca, Korisnik korisnik) {
        ImaUKolekciji imaUKolekciji = new ImaUKolekciji(vinylPloca,korisnik);
        imaUKolekecijiRepo.save(imaUKolekciji);
    }

    @Override
    public void remove(VinylPloca vinylPloca, Korisnik korisnik) {
        ImaUKolekciji imaUKolekciji = new ImaUKolekciji(vinylPloca,korisnik);
        imaUKolekecijiRepo.delete(imaUKolekciji);
    }

    @Override
    public List<ImaUKolekciji> getAllByIdKorisnik(Korisnik korisnik) {
        return imaUKolekecijiRepo.getAllByIdKorisnik(korisnik);
    }

    @Override
    public List<ImaUKolekciji> getAllByIdVinylPloca(VinylPloca ploca) {
        return imaUKolekecijiRepo.getAllByIdVinylPloca(ploca);
    }


    @Override
    public List<Optional<VinylPloca>> getMostPopularRecords() {
        Map<Long,Integer> idNumOwnersMap = new HashMap<>();
        List<ImaUKolekciji> imaju = imaUKolekecijiRepo.findAll();

        for(ImaUKolekciji i : imaju){
            if(idNumOwnersMap.containsKey(i.getIdVinylPloca().getIdVinylPloca())) {
            }else{
                idNumOwnersMap.put(i.getIdVinylPloca().getIdVinylPloca(),imaUKolekecijiRepo.countByIdVinylPloca(i.getIdVinylPloca()));
            }
        }
        LinkedHashMap<Long,Integer> reverseS = new LinkedHashMap<>();

        idNumOwnersMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x->reverseS.put(x.getKey(),x.getValue()));

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

    @Override
    public List<KorisnikDTO> getBiggestRecordOwners() {
        Map<Long,Integer> idNumRecordsMap = new HashMap<>();
        List<ImaUKolekciji> imaju = imaUKolekecijiRepo.findAll();

        for(ImaUKolekciji i : imaju){
            if(idNumRecordsMap.containsKey(i.getIdKorisnik().getIdKorisnik())) {
            }else{
                idNumRecordsMap.put(i.getIdKorisnik().getIdKorisnik(),imaUKolekecijiRepo.countByIdKorisnik(i.getIdKorisnik()));
            }
        }
        LinkedHashMap<Long,Integer> reverseS = new LinkedHashMap<>();

        idNumRecordsMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x->reverseS.put(x.getKey(),x.getValue()));

        List<KorisnikDTO> biggestO = new ArrayList<>();
        int c=0;
        for(Long key : reverseS.keySet()){
            if(c<3){
                KorisnikDTO k = KorisnikDTO.fromKorisnikToKorisnikDTO(korisnikRepo.findById(key).get());
                k.setBrojPloca(reverseS.get(key));
                biggestO.add( k);
            }else{
                break;
            }
            c++;
        }
        return biggestO;
    }
}
