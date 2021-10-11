package hr.fer.zavrsniRad.plocnik.rest.security;

import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Value("${admin.password}")
    private String adminPasswordHash;
    @Autowired
    private KorisnikService korisnikService;

    @Override
    public User loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities;
        User user;
        if ("admin".equals(korisnickoIme)) {
            authorities = commaSeparatedStringToAuthorityList("ROLE_ADMIN");
            user=new User(korisnickoIme,adminPasswordHash,authorities);
        }else {
            Korisnik korisnik = korisnikService.findByKorisnickoIme(korisnickoIme);
            if (korisnik != null) {
                authorities = commaSeparatedStringToAuthorityList("ROLE_KORISNIK");
                user=new User(korisnickoIme,korisnik.getZaporka(),authorities);
            } else {
                throw new EntityMissingException(User.class);
            }
        }
        return user;
    }
}
