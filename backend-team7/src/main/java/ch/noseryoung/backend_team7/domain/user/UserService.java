package ch.noseryoung.backend_team7.domain.user;

import ch.noseryoung.backend_team7.domain.dish.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public record UserDetailsImpl(User user) implements UserDetails {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return user.getUserRole().getRoleAuth().stream()
                    .map(a -> new SimpleGrantedAuthority(a.getAuthorityName()))
                    .toList();
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getFirstName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
    public User findById(Integer id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
