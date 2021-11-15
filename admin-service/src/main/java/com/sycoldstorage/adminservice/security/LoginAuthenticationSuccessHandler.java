package com.sycoldstorage.adminservice.security;

import com.netflix.discovery.converters.Auto;
import com.sycoldstorage.adminservice.entity.Admin;
import com.sycoldstorage.adminservice.service.AdminService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    AdminService adminService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String id = (String) authentication.getPrincipal();
        Admin admin = adminService.getAdminById(id);

        String token = Jwts.builder()
                .setSubject(admin.getId())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, "shinyoung_admin_token")
                .compact();


        response.addHeader("token", token);
        response.addHeader("adminId", admin.getId());

    }
}
