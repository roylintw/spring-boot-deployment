//package com.example.springbootdeployment.security;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        // 改寫 Response Header Www-Authenticate，防止認證失敗時，前端彈出重新輸入帳密的彈窗
//        response.setHeader("Www-Authenticate", "FormBased");
//        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 回傳 401 狀態碼
//        response.getWriter().write("Unauthorized");
//    }
//}
