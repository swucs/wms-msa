package com.sycoldstorage.adminservice.repository;

import com.sycoldstorage.adminservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 어드민 Repository
 */
public interface AdminRepository extends JpaRepository<Admin, String> {
}
