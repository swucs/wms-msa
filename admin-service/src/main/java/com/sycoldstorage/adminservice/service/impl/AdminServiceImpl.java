package com.sycoldstorage.adminservice.service.impl;

import com.sycoldstorage.adminservice.entity.Admin;
import com.sycoldstorage.adminservice.repository.AdminRepository;
import com.sycoldstorage.adminservice.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    Environment env;

    /**
     * 어드민 생성
     * @param admin
     * @return
     */
    @Override
    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        return admin;
    }

    /**
     * ID로 어드민 조회
     * @param id
     * @return
     */
    @Override
    public Admin getAdminById(String id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);

        if (adminOptional.isEmpty()) {
            throw new UsernameNotFoundException(id);
        }

        return adminOptional.get();
    }

    /**
     * Spring Security의 UserDetailsService 구현 메소드.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findById(username);

        if (!adminOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        Admin admin = adminOptional.get();

        return new User(admin.getId(), admin.getPassword()
                , true, true
                , true, true
                , new ArrayList<>());
    }
}
