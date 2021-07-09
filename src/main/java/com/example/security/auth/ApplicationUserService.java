package com.example.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public ApplicationUserService(@Qualifier("fake") ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return applicationUserRepository.getApplicationUSerByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found", s)));
    }
}
