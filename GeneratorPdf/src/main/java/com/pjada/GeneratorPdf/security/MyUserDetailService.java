package com.pjada.GeneratorPdf.security;

import com.pjada.GeneratorPdf.models.MyUserDetails;
import com.pjada.GeneratorPdf.models.User;
import com.pjada.GeneratorPdf.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(name);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found " + name));
        return user.map(MyUserDetails::new).get();
    }
}
