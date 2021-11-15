package com.sycoldstorage.adminservice.service;

import com.sycoldstorage.adminservice.entity.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {
    Admin createAdmin(Admin admin);

    Admin getAdminById(String id);
}
