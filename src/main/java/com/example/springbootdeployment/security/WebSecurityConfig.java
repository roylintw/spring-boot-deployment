package com.example.springbootdeployment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /**
     * 使用 InMemory 儲存帳號密碼
     *
     * @return InMemoryUserDetailsManager
     */
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails userTest1 = User
//                .withUsername("teljung1003@gmail.com")
//                .password("{noop}123")
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails userTest2 = User
//                .withUsername("peggy5885251@gmail.com")
//                .password("{noop}123")
//                .roles("ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userTest1, userTest2);
//    }

    /**
     * 密碼的加密機制
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                // 設定 Session 的創建實例, 使用 http basic 認證時，創造 Session 和 Cookie
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .exceptionHandling((exception)-> new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .csrf(csrf -> csrf.disable()) // 關閉 CSRF 防禦
//                .csrf(csrf -> csrf
//                        // Cookie 裡面回傳 XSRF-TOKEN 給前端
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(createCsrfHandler())
//                        // 關閉 CSRF 防禦
//                        .ignoringRequestMatchers("/register", "/memberLogin"))
                // 添加客製化的 Filter
                .addFilterBefore(new RecordMemberLoginTimeFilter(), BasicAuthenticationFilter.class)

                // 設定 Http Basic 認證和表單認證
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request

                                .requestMatchers("*").permitAll()
//                        // 註冊功能
//                        .requestMatchers("/", "/register").permitAll()
//                        .requestMatchers("/memberLogin").authenticated()
//
//                        // 權限
//                        .requestMatchers("/hello").hasRole("ADMIN")
//                        .requestMatchers("/helloWorld").hasRole("NORMAL_MEMBER")
//
//                        // Movie功能
//                        .requestMatchers("/watchFreeMovie").hasAnyRole("NORMAL_MEMBER")
//                        .requestMatchers("/watchVipMovie").hasAnyRole("VIP_MEMBER", "ADMIN")
//
//                        // 訂閱和取消訂閱功能
//                        .requestMatchers("/subscribe", "/unsubscribe").hasAnyRole("NORMAL_MEMBER", "ADMIN")

                        .anyRequest().denyAll() // deny-by-default
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                )
                .cors(Customizer.withDefaults()) // 關閉 CORS 防禦
                // 方法1.(5-2)
                // 增加CORS設定，解決跨域問題
//                .cors(cors -> cors.configurationSource(createCorsConfig()))
                // 方法2.關閉Spring Security的授權檢查機制
//                .cors(Customizer.withDefaults()) // disable this line to reproduce the CORS 401
                .build();
    }

    /**
     * 方法2. 新增 Cors 設定，解決 CORS 問題(5-2)
     *
     * @return CorsConfigurationSource
     */
    private CorsConfigurationSource createCorsConfig() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173", "http://example.com", "https://vue-deployment-xpf6zdjama-de.a.run.app")); // 請求來源
        config.setAllowedHeaders(List.of("*")); // request header
        config.setAllowedMethods(List.of("*")); // http method
        config.setAllowCredentials(true); // 是否允許前端帶上 cookie
        config.setMaxAge(3600L); // 設定 Preflight 請求的結果，可以被瀏覽器 cache 幾秒

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    private CsrfTokenRequestAttributeHandler createCsrfHandler() {
        CsrfTokenRequestAttributeHandler csrfHandler = new CsrfTokenRequestAttributeHandler();
        csrfHandler.setCsrfRequestAttributeName(null);

        return csrfHandler;
    }

}
