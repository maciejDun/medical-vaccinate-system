package com.dunin.medicalvaccinatesystem.security.userDetails;

import com.dunin.medicalvaccinatesystem.dao.user.model.UserFromDB;
import com.dunin.medicalvaccinatesystem.dao.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserFromDB> user = userRepository.findByUserName(userName);
        return user.map(AdminUserDetails::new)
                   .orElseThrow(() -> new UsernameNotFoundException("Username not exists"));
    }
}
