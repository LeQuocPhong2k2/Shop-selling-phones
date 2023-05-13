package com.iuh.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf().disable();

                http.authorizeHttpRequests()
                                .requestMatchers("/home/**", "/san-pham/**", "/register", "/api/**", "/logout",
                                                "/css/**", "/js/**", "/img/**")
                                .permitAll();

                http.authorizeHttpRequests().requestMatchers("/cart/**").hasAnyAuthority("USER");

                http.authorizeHttpRequests().requestMatchers("/admin/**").hasAnyAuthority("ADMIN");

                http.authorizeHttpRequests().anyRequest().authenticated();

                http.authorizeHttpRequests().and().exceptionHandling().accessDeniedPage("/403");

                http
                                .authorizeHttpRequests()
                                .and()
                                .formLogin()
                                .loginPage("/api/auth/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/home")
                                .failureUrl("/login?error=true")
                                .and()
                                .logout()
                                .logoutSuccessUrl("/home")
                                .permitAll();

                return http.build();
        }

}