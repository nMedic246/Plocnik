package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.helpers.AdminDTO;
import hr.fer.zavrsniRad.plocnik.helpers.KorisnikDTO;
import hr.fer.zavrsniRad.plocnik.helpers.LoginRequest;
import hr.fer.zavrsniRad.plocnik.rest.security.UserDetailsServiceImp;
import hr.fer.zavrsniRad.plocnik.service.KorisnikService;
import hr.fer.zavrsniRad.plocnik.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("${admin.password}")
    private String adminPasswordHash;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;
   

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> LoginAuthentification(@RequestBody LoginRequest req){
        Korisnik loggedKorisnik = korisnikService.findByKorisnickoIme(req.getKorisnickoIme());

        if(loggedKorisnik != null && new BCryptPasswordEncoder().matches(req.getZaporka(),loggedKorisnik.getZaporka())){
            return ResponseEntity.status(HttpStatus.OK).body(KorisnikDTO.fromKorisnikToKorisnikDTO(loggedKorisnik));
        }else if(req.getKorisnickoIme().equals("admin") && new BCryptPasswordEncoder().matches(req.getZaporka(),adminPasswordHash)){
            userDetailsServiceImp.loadUserByUsername("admin");
            AdminDTO admin= new AdminDTO(req.getKorisnickoIme());
            return ResponseEntity.status(HttpStatus.OK).body(admin);
        }else{
            return ResponseEntity.badRequest().body(new RequestDeniedException("Netočno korisničko ime ili lozinka"));
        }
    }

}
