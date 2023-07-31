package com.vick7.library.service.impl;

import com.vick7.library.dto.AdminDto;
import com.vick7.library.model.Admin;
import com.vick7.library.model.Role;
import com.vick7.library.repository.AdminRepository;
import com.vick7.library.repository.RoleRepository;
import com.vick7.library.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {
    private BCryptPasswordEncoder passwordEncoder;
    private AdminRepository adminRepository;
    private RoleRepository roleRepository;
    public AdminServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository){
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }

}
