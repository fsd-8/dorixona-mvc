package net.idrok.dorixona.security;

import net.idrok.dorixona.model.User;
import net.idrok.dorixona.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).map(user -> new SecUser(user)).orElseThrow(()->new UsernameNotFoundException("not found"));
    }
}
