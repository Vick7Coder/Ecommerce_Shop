package com.vick7.library.service;

import com.vick7.library.dto.AdminDto;
import com.vick7.library.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);
    Admin save(AdminDto adminDto);

}
