package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.*;
import hr.fer.zavrsniRad.plocnik.helpers.IzdavackaKucaDTO;
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
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/izdavackaKuca")
public class IzdavackaKucaController {

    @Autowired
    private IzdavackaKucaService izdavackaKucaService;

    @Autowired
    private VinylPlocaService vinylPlocaService;

    @Autowired
    private SlikaService slikaService;
    @Autowired
    private MjestoService mjestoService;
    @Autowired
    private VlasnikService vlasnikService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> getIzdavackaKucaById(@PathVariable("id") String id) {
        try {
            IzdavackaKuca izdavackaKuca =izdavackaKucaService.fetch(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(izdavackaKuca);
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @PostMapping("/dodajIzdavackuKucu")
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> createIzdavackaKuca(@RequestBody IzdavackaKucaDTO izdavackaKucaDTO, @AuthenticationPrincipal User user){
        try{
            Mjesto mjesto =mjestoService.fetch(izdavackaKucaDTO.getIdMjesto()) ;
            Vlasnik vlasnik = vlasnikService.fetch(izdavackaKucaDTO.getIdVlasnik());

            IzdavackaKuca izdavackaKuca = new IzdavackaKuca(izdavackaKucaDTO.getNazivIzdavackaKuca(),izdavackaKucaDTO.getInfoIzdavackaKuca(),mjesto,vlasnik);
            izdavackaKuca = izdavackaKucaService.createVinylPloca(izdavackaKuca);

            if(izdavackaKuca != null){
                return ResponseEntity.status(HttpStatus.OK).body(izdavackaKucaDTO);
            }
        } catch (RequestDeniedException ex){
            System.out.println(ex);
            return ResponseEntity.badRequest().body(ex);
        }
        return ResponseEntity.badRequest().body(new RequestDeniedException("Neispravan zahtjev"));
    }

    @DeleteMapping("/{id}/obrisiIzdavackuKucu")
    @Secured({"ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<Object> deleteIzdavackaKuca( @PathVariable("id") String izdavackaKucaId, @AuthenticationPrincipal User user) {
        try {
            IzdavackaKuca izdavackaKuca = izdavackaKucaService.fetch(Long.valueOf(izdavackaKucaId));
            if(izdavackaKuca!=null){
                IzdavackaKuca deletedIzdavackaKuca = izdavackaKucaService.deleteIzdavackaKuca(izdavackaKuca.getIdIzdavackaKuca());
                return ResponseEntity.status(HttpStatus.OK).body(deletedIzdavackaKuca);}
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
        return ResponseEntity.badRequest().body(new RequestDeniedException("Samo admin može brisati!"));
    }

    @GetMapping("/list")
    public List<IzdavackaKuca> listPloce() {
        return izdavackaKucaService.listAll();
    }

    @RequestMapping(value = "/{id}/updatePhoto", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> updatePhoto(@PathVariable("id") String id, @RequestPart(value = "file") MultipartFile multipartFile){
        try {
            IzdavackaKuca izdavackaKuca=izdavackaKucaService.fetch(Long.valueOf(id));
            if (izdavackaKuca != null) {
                byte[] bytes = multipartFile.getBytes();
                slikaService.createSlika(new Slika(1L, Base64.getEncoder().encodeToString(bytes),null,null,null,izdavackaKuca));

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
            IzdavackaKuca izdavackaKuca=izdavackaKucaService.fetch(Long.valueOf(id));
            Slika sl = slikaService.findByIzdavackaKuca(izdavackaKuca);
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

    @GetMapping("/popisPloca/{id}")
    public ResponseEntity<Object> getPloce(@PathVariable("id") String id){
        try {
            List<VinylPloca> ploce=vinylPlocaService.getAllByIdIzdavackaKuca(izdavackaKucaService.fetch(Long.valueOf(id)));
            return ResponseEntity.status(HttpStatus.OK).body(ploce);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/filter/{filter}")
    public List<IzdavackaKuca> filterPloca(@PathVariable("filter") String filter){
        if(filter.trim().equals("")){
            return izdavackaKucaService.listAll();
        }else{
            return izdavackaKucaService.filterIzdavackaKuca(filter);
        }
    }
}
