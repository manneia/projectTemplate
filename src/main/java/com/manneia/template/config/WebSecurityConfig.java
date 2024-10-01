package com.manneia.template.config;

import com.manneia.template.common.CommonProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author luokaixuan
 * @description com.manneia.template.config
 * @created 2024/9/15 18:33
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Resource
    private CommonProperties commonProperties;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/manneia/swagger-ui.html").permitAll() // 配置不受保护的路径
                        .anyRequest().authenticated() // 其它所有请求都需要认证
                )
                .formLogin(form -> form
                        .loginPage("/login")// 自定义登录页面
                        .permitAll())
                .logout(LogoutConfigurer::permitAll); // 登出功能可以被任何用户访问
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = bCryptPasswordEncoder();
        return new InMemoryUserDetailsManager(
                User
                        .withUsername(commonProperties.getUsername())
                        .password(encoder.encode(commonProperties.getPassword()))
                        .roles("USER").build()
        );
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/static/**", "/css/**", "/images/**"); // 忽略静态资源
    }
}
