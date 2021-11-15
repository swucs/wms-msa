package com.sycoldstorage.adminservice;

import com.sycoldstorage.adminservice.entity.Admin;
import com.sycoldstorage.adminservice.service.AdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceApplicationTests {

    @Autowired
    AdminService adminService;

    @Test
    @DisplayName("어드민 생성 : 암호화를 위한")
    void createAdmin() {

        Admin admin = Admin.builder()
                .id("heavy2")
                .name("강석태")
                .password("1234")
                .build();

        adminService.createAdmin(admin);

        admin = Admin.builder()
                .id("hardline")
                .name("김성배")
                .password("12345678")
                .build();

        adminService.createAdmin(admin);

    }

}
