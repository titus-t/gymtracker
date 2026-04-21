package backend.gymtracker.web;

import backend.gymtracker.domain.AppUser;
import backend.gymtracker.domain.AppUserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    
    private final AppUserRepository repository;

    public UserDetailServiceImpl(AppUserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = repository.findByUsername(username);
        if (curruser == null) {
            throw new UsernameNotFoundException("Käyttäjää ei löytynyt.");
        }
        return new org.springframework.security.core.userdetails.User(
            username, 
            curruser.getPasswordHash(),
            AuthorityUtils.createAuthorityList(curruser.getRole())
        );
    }
}