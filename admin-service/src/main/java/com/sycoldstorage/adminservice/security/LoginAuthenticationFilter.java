package com.sycoldstorage.adminservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sycoldstorage.adminservice.dto.LoginRequest;
import com.sycoldstorage.adminservice.entity.Admin;
import com.sycoldstorage.adminservice.service.AdminService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

//    private AdminService adminService;
//    private Environment env;

    public LoginAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login"));
//        this.adminService = adminService;
//        this.env = env;
    }

    /**
     * 로그인 시도
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            if (StringUtils.isEmpty(loginRequest.getId()) || StringUtils.isEmpty(loginRequest.getPassword())) {
                throw new IllegalArgumentException("Admin ID or Password is empty.");
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getId(), loginRequest.getPassword(), new ArrayList<>());
            return super.getAuthenticationManager().authenticate(authenticationToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    /**
//     * 인증 성공시
//     * @param request
//     * @param response
//     * @param chain
//     * @param authResult
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response
//            , FilterChain chain, Authentication authResult) throws IOException, ServletException {
//
//        String id = ((User) authResult.getPrincipal()).getUsername();
//        Admin admin = adminService.getAdminById(id);
//
//        String token = Jwts.builder()
//                .setSubject(admin.getId())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//                .signWith(SignatureAlgorithm.HS512, "shinyoung_admin_token")
//                .compact();
//
//        logger.error("로그인 성공");
//
//        response.addHeader("token", token);
//        response.addHeader("adminId", admin.getId());
//    }
}
