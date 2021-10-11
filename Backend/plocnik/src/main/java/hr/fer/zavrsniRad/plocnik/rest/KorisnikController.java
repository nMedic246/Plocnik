package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.*;
import hr.fer.zavrsniRad.plocnik.helpers.KorisnikDTO;
import hr.fer.zavrsniRad.plocnik.helpers.ReplyDTO;
import hr.fer.zavrsniRad.plocnik.helpers.SlikaDTO;
import hr.fer.zavrsniRad.plocnik.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private SlikaService slikaService;

    @Autowired
    private ImaUKolekcijiService imaUKolekcijiService;

    @Autowired
    private ZeliService zeliService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Object> createKorisnik(@RequestBody Korisnik korisnikReq){
        try{
            Korisnik korisnik = korisnikService.createKorisnik(korisnikReq);

            if(korisnik != null){
                return ResponseEntity.status(HttpStatus.OK).body(KorisnikDTO.fromKorisnikToKorisnikDTO(korisnik));
            }
        } catch (RequestDeniedException ex){
            System.out.println(ex);
            return ResponseEntity.badRequest().body(ex);
        }
        return ResponseEntity.badRequest().body(new RequestDeniedException("Neispravan zahtjev"));

    }

    @PutMapping("/updateKorisnik/{korisnickoIme}")
    @Secured({"ROLE_KORISNIK"})
    @Transactional
    @ResponseBody
    public ResponseEntity<Object> updateKorisnik(@PathVariable("korisnickoIme") String korisnickoIme, @AuthenticationPrincipal User user,@RequestBody Korisnik korisniReq) {
        if(korisnickoIme.equals(user.getUsername())){
            try {
                Korisnik korisnik = korisnikService.findByKorisnickoIme(korisnickoIme);
                String novoKorisnickoIme =null;
                String novaZaporka =null;
                if(!korisniReq.getKorisnickoIme().equals(korisnickoIme)){
                    novoKorisnickoIme=korisniReq.getKorisnickoIme();
                }
                if(!korisniReq.getZaporka().trim().equals("")){
                    novaZaporka=korisniReq.getZaporka();
                }
                if (korisnik != null) {
                    Korisnik noviKorisnik = new Korisnik(korisnik.getIdKorisnik(),novoKorisnickoIme,korisnik.getEmail(),novaZaporka);
                    Korisnik r = noviKorisnik;
                    noviKorisnik = korisnikService.updateKorisnik(noviKorisnik);
                    if(r.getKorisnickoIme()==null){
                        r.setKorisnickoIme(korisnickoIme);
                    }
                    return ResponseEntity.status(HttpStatus.OK).body(KorisnikDTO.fromKorisnikToKorisnikDTO(r));
                }
            } catch (RequestDeniedException ex) {
                return ResponseEntity.badRequest().body(ex);
            }
        }
        return ResponseEntity.badRequest().body(new RequestDeniedException("Korisnik može mijenjati samo svoje podatke!"));
    }

    @DeleteMapping("/{korisnickoIme}/obrisiProfil")
    @Secured({"ROLE_KORISNIK"})
    @ResponseBody
    public ResponseEntity<Object> deleteProfile( @PathVariable("korisnickoIme") String korisnickoIme, @AuthenticationPrincipal User user) {
        if(korisnickoIme.equals(user.getUsername())) {
            try {

                Korisnik korisnik = korisnikService.findByKorisnickoIme(korisnickoIme);
                if (korisnik != null) {
                    Korisnik deletedKorisnik  = korisnikService.deleteKorisnik(korisnik.getIdKorisnik());
                    return ResponseEntity.status(HttpStatus.OK).body(new ReplyDTO("Uspješno obrisan korisnik!"));
                }
            } catch (RequestDeniedException ex) {
                return ResponseEntity.badRequest().body(ex);
            }

        }
        return ResponseEntity.badRequest().body(new RequestDeniedException("Samo trenutni korisnik može obrisati svoj profil!"));
    }

    @GetMapping("/list")
    public List<Korisnik> listKorisnik(){
        return korisnikService.listAll();
    }

    @RequestMapping(value = "/{id}/updatePhoto", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> updatePhoto(@PathVariable("id") String id, @RequestPart(value = "file") MultipartFile multipartFile){
        try {
            Korisnik korisnik =korisnikService.fetch(Long.valueOf(id));
            if (korisnik != null) {
                byte[] bytes = multipartFile.getBytes();
                slikaService.createSlika(new Slika(1L, Base64.getEncoder().encodeToString(bytes),korisnik,null,null,null));

                return ResponseEntity.status(HttpStatus.OK).body("Slika postavljena");}

        } catch (RequestDeniedException | IOException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @GetMapping("/slika/{korisnickoIme}")
    @Transactional
    public ResponseEntity<Object> getKorisnikSlika(@PathVariable("korisnickoIme") String korisnickoIme) {
        try{
            Korisnik korisnik=korisnikService.findByKorisnickoIme(korisnickoIme);
            Slika sl = slikaService.findByKorisnik(korisnik);
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

    @GetMapping("/{korisnickoIme}")
    public ResponseEntity<Object> getKorisnikByKorIme(@PathVariable("korisnickoIme") String korisnickoIme){
        try {
            Korisnik korisnik=korisnikService.findByKorisnickoIme(korisnickoIme);
            return ResponseEntity.status(HttpStatus.OK).body(KorisnikDTO.fromKorisnikToKorisnikDTO(korisnik));
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/{korisnickoIme}/popisPloca")
    public ResponseEntity<Object> getMojePloce(@PathVariable("korisnickoIme") String korisnickoIme){
        try {
            List<ImaUKolekciji> mojePloce=imaUKolekcijiService.getAllByIdKorisnik(korisnikService.findByKorisnickoIme(korisnickoIme));
            return ResponseEntity.status(HttpStatus.OK).body(mojePloce);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/{korisnickoIme}/listaZelja")
    public ResponseEntity<Object> getListaZelja(@PathVariable("korisnickoIme") String korisnickoIme){
        try {
            List<Zeli> zelimPloce =zeliService.getAllByIdKorisnik(korisnikService.findByKorisnickoIme(korisnickoIme));
            return ResponseEntity.status(HttpStatus.OK).body(zelimPloce);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/najveciKolekcionari")
    public List<KorisnikDTO> getBiggestRecordOwners() {
        return imaUKolekcijiService.getBiggestRecordOwners();
    }
}
