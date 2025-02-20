package com.task.org.config;

import com.task.org.enums.Role;
import com.task.org.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtFilter jwtFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtFilter jwtFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/api/users/login", "/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasAuthority("VIEW_EMPLOYEES")
                        .requestMatchers(HttpMethod.POST, "/api/employees/**").hasAuthority("EDIT_EMPLOYEES")
                        .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasAuthority("EDIT_EMPLOYEES")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasAuthority("EDIT_EMPLOYEES")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasAuthority("EDIT_EMPLOYEES")
                        .requestMatchers(HttpMethod.GET, "/api/projects/**").hasAuthority("VIEW_PROJECTS")
                        .requestMatchers(HttpMethod.POST, "/api/projects/**").hasAuthority("EDIT_PROJECTS")
                        .requestMatchers(HttpMethod.PATCH, "/api/projects/**").hasAuthority("EDIT_PROJECTS")
                        .requestMatchers(HttpMethod.PUT, "/api/projects/**").hasAuthority("EDIT_PROJECTS")
                        .requestMatchers(HttpMethod.DELETE, "/api/projects/**").hasAuthority("EDIT_PROJECTS")
                        .requestMatchers(HttpMethod.GET, "/api/departments/**").hasAuthority("VIEW_DEPARTMENTS")
                        .requestMatchers(HttpMethod.POST, "/api/departments/**").hasAuthority("EDIT_DEPARTMENTS")
                        .requestMatchers(HttpMethod.PATCH, "/api/departments/**").hasAuthority("EDIT_DEPARTMENTS")
                        .requestMatchers(HttpMethod.PUT, "/api/departments/**").hasAuthority("EDIT_DEPARTMENTS")
                        .requestMatchers(HttpMethod.DELETE, "/api/departments/**").hasAuthority("EDIT_DEPARTMENTS")
                        .requestMatchers(HttpMethod.GET, "/api/organizations/**").hasAuthority("VIEW_ORGANIZATIONS")
                        .requestMatchers(HttpMethod.POST, "/api/organizations/**").hasAuthority("EDIT_ORGANIZATIONS")
                        .requestMatchers(HttpMethod.PATCH, "/api/organizations/**").hasAuthority("EDIT_ORGANIZATIONS")
                        .requestMatchers(HttpMethod.PUT, "/api/organizations/**").hasAuthority("EDIT_ORGANIZATIONS")
                        .requestMatchers(HttpMethod.DELETE, "/api/organizations/**").hasAuthority("EDIT_ORGANIZATIONS")
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, authException) -> {
                            throw authException;
                        }).accessDeniedHandler((request, response, accessDeniedException) -> {
                            throw accessDeniedException;
                        })
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }
}
