package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.*;
import hr.fer.zavrsniRad.plocnik.helpers.IzvodacDTO;
import hr.fer.zavrsniRad.plocnik.helpers.SlikaDTO;
import hr.fer.zavrsniRad.plocnik.helpers.VinylPlocaDTO;
import hr.fer.zavrsniRad.plocnik.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/izvodac")
public class IzvodacController {

    @Autowired
    private IzvodacService izvodacService;
    @Autowired
    private GrupaService grupaService;
    @Autowired
    private GlazbenikService glazbenikService;
    @Autowired
    private SlikaService slikaService;
    @Autowired
    private DrzavaService drzavaService;

    @GetMapping("/list")
    public List<Izvodac> listIzvodac (){
        return izvodacService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIzvodacById(@PathVariable("id") String id) {
        try {
            Izvodac izvodac=izvodacService.fetch(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(izvodac);
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
    @PostMapping("/dodajIzvodaca")
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> createVinylPloca(@RequestBody IzvodacDTO izvodacDTO, @AuthenticationPrincipal User user){
        try{
            Drzava drzava = drzavaService.fetch(izvodacDTO.getIdDrzava());
            Izvodac izvodac = new Izvodac(izvodacDTO.getNazivIzvodac(),izvodacDTO.getInfoIzvodac(),izvodacDTO.getJeGrupa(),drzava);
            izvodac =izvodacService.createIzvodac(izvodac);

            if(izvodac.getJegrupa()==1){
                Grupa grupa= new Grupa(izvodac,izvodacDTO.getPocDjelovanjaGrupa(),izvodacDTO.getKrajDjelovanjaGrupa());
                grupa= grupaService.createGrupa(grupa);

            }else{
                Glazbenik glazbenik = new Glazbenik(izvodac,izvodacDTO.getDatumRod(),izvodacDTO.getDatumSmrti());
                glazbenik=glazbenikService.createGlazbenik(glazbenik);
            }
            if(izvodac != null){
                return ResponseEntity.status(HttpStatus.OK).body(izvodacDTO);
            }
        } catch (RequestDeniedException ex){
            System.out.println(ex);
            return ResponseEntity.badRequest().body(ex);
        }
        return ResponseEntity.badRequest().body(new RequestDeniedException("Neispravan zahtjev"));
    }
    @DeleteMapping("/{id}/obrisiIzvodaca")
    @Secured({"ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<Object> deleteVinyl( @PathVariable("id") String izvodacId, @AuthenticationPrincipal User user) {
        try {
            Izvodac izvodac = izvodacService.fetch(Long.valueOf(izvodacId));
            if(izvodac!=null){
                Izvodac deletedIzvodac =izvodacService.deleteIzvodac(izvodac.getIdIzvodac());
                return ResponseEntity.status(HttpStatus.OK).body(deletedIzvodac);}
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }

        return ResponseEntity.badRequest().body(new RequestDeniedException("Samo admin može brisati!"));
    }

    @RequestMapping(value = "/{id}/updatePhoto", method = RequestMethod.POST)
    @CrossOrigin(origins = {"https://hocuvan-deployment.herokuapp.com","http://localhost:4200"})
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> updatePhoto(@PathVariable("id") String id, @RequestPart(value = "file") MultipartFile multipartFile){
        try {
            Izvodac izvodac=izvodacService.fetch(Long.valueOf(id));
            if (izvodac != null) {
                byte[] bytes = multipartFile.getBytes();
                slikaService.createSlika(new Slika(1L, Base64.getEncoder().encodeToString(bytes),null,null,izvodac,null));

                return ResponseEntity.status(HttpStatus.OK).body("Slika postavljena");}

        } catch (RequestDeniedException | IOException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @GetMapping("/slika/{id}")
    @Transactional
    public ResponseEntity<Object> getIzvodacImage(@PathVariable("id") String id) {
        try{
            Izvodac izvodac=izvodacService.fetch(Long.valueOf(id));
            Slika sl = slikaService.findByIzvodac(izvodac);
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

    @GetMapping("/filter/{filter}")
    public List<Izvodac> filterPloca(@PathVariable("filter") String filter){
        if(filter.trim().equals("")){
            return izvodacService.listAll();
        }else{
            return izvodacService.filterIzvodac(filter);
        }
    }
}
