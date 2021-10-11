package hr.fer.zavrsniRad.plocnik.rest;
import hr.fer.zavrsniRad.plocnik.domain.*;
import hr.fer.zavrsniRad.plocnik.helpers.KorisnikDTO;
import hr.fer.zavrsniRad.plocnik.helpers.ReplyDTO;
import hr.fer.zavrsniRad.plocnik.helpers.SlikaDTO;
import hr.fer.zavrsniRad.plocnik.helpers.VinylPlocaDTO;
import hr.fer.zavrsniRad.plocnik.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.management.OperatingSystemMXBean;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vinylPloca")
public class VinylPlocaController {

    @Autowired
    private VinylPlocaService vinylPlocaService;
    @Autowired
    private IzvodacService izvodacService;
    @Autowired
    private SlikaService slikaService;
    @Autowired
    private IzdavackaKucaService izdavackaKucaService;
    @Autowired
    private SadrziService sadrziService;
    @Autowired
    private JeZanraService jeZanraService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private ImaUKolekcijiService imaUKolekcijiService;
    @Autowired
    private ZeliService zeliService;
    @Autowired
    private VrstaService vrstaService;
    @Autowired
    private  ZanrService zanrService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> getVinylPlocaById(@PathVariable("id") String id) {
        try {
            VinylPloca vinylPloca=vinylPlocaService.fetch(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(vinylPloca);
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/list")
    public List<VinylPloca> listPloce() {
        return vinylPlocaService.listAll();
    }

    @PostMapping("/dodajPlocu")
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> createVinylPloca(@RequestBody VinylPlocaDTO vinylPlocaDTO, @AuthenticationPrincipal User user){
        try{
            Izvodac izvodac = izvodacService.fetch(vinylPlocaDTO.getIdIzvodac());
            IzdavackaKuca izdavackaKuca = izdavackaKucaService.fetch(vinylPlocaDTO.getIdIzdavackaKuca());
            VrstaPloce vrsta = vrstaService.fetch(vinylPlocaDTO.getIdVrsta());
            Zanr zanr = zanrService.fetch(vinylPlocaDTO.getIdZanr());

            VinylPloca vinylPloca = new VinylPloca(vinylPlocaDTO.getNazivVinylPloca(),vinylPlocaDTO.getGodinaIzdanja(),vinylPlocaDTO.getInfoVinylPloca(),vinylPlocaDTO.getVerzija(),izdavackaKuca,vrsta,izvodac);
            vinylPloca =vinylPlocaService.createVinylPloca(vinylPloca);

            if(vinylPloca != null){
                JeZanra jeZanra = new JeZanra(zanr,vinylPloca);
                jeZanra = jeZanraService.createJZ(jeZanra);
                return ResponseEntity.status(HttpStatus.OK).body(vinylPlocaDTO);
            }
        } catch (RequestDeniedException ex){
            System.out.println(ex);
            return ResponseEntity.badRequest().body(ex);
        }
        return ResponseEntity.badRequest().body(new RequestDeniedException("Neispravan zahtjev"));
    }

    @DeleteMapping("/{id}/obrisiVinylPlocu")
    @Secured({"ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<Object> deleteVinyl( @PathVariable("id") String vinylId, @AuthenticationPrincipal User user) {
        try {
            VinylPloca vinylPloca = vinylPlocaService.fetch(Long.valueOf(vinylId));
            if(vinylPloca!=null){
                VinylPloca deletedVinylPloca = vinylPlocaService.deleteVinylPloca(vinylPloca.getIdVinylPloca());
                return ResponseEntity.status(HttpStatus.OK).body(deletedVinylPloca);}
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }

        return ResponseEntity.badRequest().body(new RequestDeniedException("Samo admin može brisati!"));
    }
    @RequestMapping(value = "/{id}/updatePhoto", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> updatePhoto(@PathVariable("id") String id, @RequestPart(value = "file") MultipartFile multipartFile){
        try {
            VinylPloca vinylPloca=vinylPlocaService.fetch(Long.valueOf(id));
            if (vinylPloca != null) {
                byte[] bytes = multipartFile.getBytes();
                slikaService.createSlika(new Slika(1L, Base64.getEncoder().encodeToString(bytes),null,vinylPloca,null,null));

                return ResponseEntity.status(HttpStatus.OK).body("Slika postavljena");}

        } catch (RequestDeniedException | IOException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @GetMapping("/slika/{id}")
    @Transactional
    public ResponseEntity<Object> getVinylPlocaSlika(@PathVariable("id") String id) {
        try{
            VinylPloca vinylPloca=vinylPlocaService.fetch(Long.valueOf(id));
            Slika sl = slikaService.findByVinylPloca(vinylPloca);
            if(sl==null){
                return ResponseEntity.status(HttpStatus.OK).body(new SlikaDTO(null));
            }else {
                String slika= sl.getSlika();
                return ResponseEntity.status(HttpStatus.OK).body(new SlikaDTO(slika));
            }
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/popisPjesama/{id}")
    public ResponseEntity<Object> getPjesme(@PathVariable("id") String id){
        try {
            List<Sadrzi> pjesme=sadrziService.getAllByIdVinylPloca(vinylPlocaService.fetch(Long.valueOf(id)));
            return ResponseEntity.status(HttpStatus.OK).body(pjesme);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
    @GetMapping("/zanr/{id}")
    public ResponseEntity<Object> getZanr(@PathVariable("id") String id){
        try {
            List<JeZanra> zanr=jeZanraService.getAllByIdVinylPloca(vinylPlocaService.fetch(Long.valueOf(id)));
            return ResponseEntity.status(HttpStatus.OK).body(zanr);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/izvodacDiskografija/{id}")
    public ResponseEntity<Object> getDiskografija(@PathVariable("id") String id) {
        try {
            List<VinylPloca> ploce = vinylPlocaService.getAllByIdIzvodac(izvodacService.fetch(Long.valueOf(id)));
            return ResponseEntity.status(HttpStatus.OK).body(ploce);
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/dodajUMojePloce/{id}")
    @Secured("ROLE_KORISNIK")
    public ResponseEntity<Object> selectImamPlocu(@PathVariable("id") String id, @AuthenticationPrincipal User user) {
        try {
            VinylPloca ploca = vinylPlocaService.fetch(Long.valueOf(id));
            Korisnik korisnik=korisnikService.findByKorisnickoIme(user.getUsername());
            imaUKolekcijiService.save(ploca,korisnik);
            ReplyDTO reply = new ReplyDTO("Ploča " + ploca.getNazivVinylPloca() + " je dodana u kolekciju!");
            return ResponseEntity.status(HttpStatus.OK).body(reply);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
    @GetMapping("/ukloniIzMojihPloca/{id}")
    @Secured("ROLE_KORISNIK")
    public ResponseEntity<Object> deselectImamPlocu(@PathVariable("id") String id, @AuthenticationPrincipal User user) {
        try {
            VinylPloca ploca = vinylPlocaService.fetch(Long.valueOf(id));
            Korisnik korisnik=korisnikService.findByKorisnickoIme(user.getUsername());
            imaUKolekcijiService.remove(ploca,korisnik);
            ReplyDTO reply = new ReplyDTO("Ploča " + ploca.getNazivVinylPloca() + " je uklonjena iz favorita!");

            return ResponseEntity.status(HttpStatus.OK).body(reply);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
    @GetMapping("/dodajUListuZelja/{id}")
    @Secured("ROLE_KORISNIK")
    public ResponseEntity<Object> selectZelimPlocu(@PathVariable("id") String id, @AuthenticationPrincipal User user) {
        try {

            VinylPloca ploca = vinylPlocaService.fetch(Long.valueOf(id));
            Korisnik korisnik=korisnikService.findByKorisnickoIme(user.getUsername());
            System.out.println(ploca.getNazivVinylPloca()+" "+korisnik.getKorisnickoIme());
            zeliService.save(ploca,korisnik);
            ReplyDTO reply = new ReplyDTO("Ploča " + ploca.getNazivVinylPloca() + " je dodana u listu zelja!");
            return ResponseEntity.status(HttpStatus.OK).body(reply);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
    @GetMapping("/ukloniIzListeZelja/{id}")
    @Secured("ROLE_KORISNIK")
    public ResponseEntity<Object> deselectZelimPlocu(@PathVariable("id") String id, @AuthenticationPrincipal User user) {
        try {
            VinylPloca ploca = vinylPlocaService.fetch(Long.valueOf(id));
            Korisnik korisnik=korisnikService.findByKorisnickoIme(user.getUsername());
            zeliService.remove(ploca,korisnik);
            ReplyDTO reply = new ReplyDTO("Ploča " + ploca.getNazivVinylPloca() + " je uklonjena iz liste zelja!");

            return ResponseEntity.status(HttpStatus.OK).body(reply);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/imajuPlocu/{id}")
    @Secured({"ROLE_KORISNIK","ROLE_ADMIN"})
    public ResponseEntity<Object> getVlasniciPloca(@PathVariable("id") String id, @AuthenticationPrincipal User user) {
        try {
            List<ImaUKolekciji> imaju=imaUKolekcijiService.getAllByIdVinylPloca(vinylPlocaService.fetch(Long.valueOf(id)));
            return ResponseEntity.status(HttpStatus.OK).body(imaju);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/zelePlocu/{id}")
    @Secured({"ROLE_KORISNIK","ROLE_ADMIN"})
    public ResponseEntity<Object> getZelePlocu(@PathVariable("id") String id, @AuthenticationPrincipal User user) {
        try {
            List<Zeli> zele=zeliService.getAllByIdVinylPloca(vinylPlocaService.fetch(Long.valueOf(id)));
            return ResponseEntity.status(HttpStatus.OK).body(zele);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/filter/{filter}")
    public List<VinylPloca> filterPloca(@PathVariable("filter") String filter){
        if(filter.trim().equals("")){
            return vinylPlocaService.listAll();
        }else{
            return vinylPlocaService.filterVinylPloca(filter);
        }
    }

    @GetMapping("/najpopularnijeVinylPloce")
    public List<Optional<VinylPloca>> getMostPopularRecords() {
        return imaUKolekcijiService.getMostPopularRecords();
    }

    @GetMapping("/najtrazenijeVinylPloce")
    public List<Optional<VinylPloca>> getMostWantedRecords(){
        return  zeliService.getMostWantedRecords();
    }

    @GetMapping("/najboljeOcjenjeneVinylPloce")
    public List<VinylPloca> getBestRecords(){
        return vinylPlocaService.getBestRecords();
    }


}
