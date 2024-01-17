package org.example.jwtnewversion.service;

import org.example.jwtnewversion.repository.OurUsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OurUserDetailsService implements UserDetailsService {

    private OurUsersRepo ourUsersRepo;

    public OurUserDetailsService(OurUsersRepo ourUsersRepo) {
        this.ourUsersRepo = ourUsersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return ourUsersRepo.findByEmail(username).orElseThrow();
    }
}
