package com.dunin.medicalvaccinatesystem.security.userDetails;

import com.dunin.medicalvaccinatesystem.dao.UserFromDB;
import com.dunin.medicalvaccinatesystem.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VaccinateUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserFromDB> user = userRepository.findByUserName(userName);
        return user.map(VaccinateUserDetails::new)
                   .orElseThrow(() -> new UsernameNotFoundException("Username not exists"));
    }
}
