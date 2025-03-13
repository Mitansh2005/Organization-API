package com.task.org.config;

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
    private static final String EMPLOYEE_URL="/api/employees/**";
    private static final String PROJECT_URL="/api/projects/**";
    private static final String ORGANIZATION_URL="/api/organizations/**";
    private static final String DEPARTMENT_URL="/api/organizations/**";
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
                        .requestMatchers(HttpMethod.GET, EMPLOYEE_URL).hasAuthority("VIEW_EMPLOYEES")
                        .requestMatchers(HttpMethod.POST, EMPLOYEE_URL).hasAuthority("EDIT_EMPLOYEES")
                        .requestMatchers(HttpMethod.PATCH, EMPLOYEE_URL).hasAuthority("EDIT_EMPLOYEES")
                        .requestMatchers(HttpMethod.PUT, EMPLOYEE_URL).hasAuthority("EDIT_EMPLOYEES")
                        .requestMatchers(HttpMethod.DELETE, EMPLOYEE_URL).hasAuthority("EDIT_EMPLOYEES")
                        .requestMatchers(HttpMethod.GET, PROJECT_URL).hasAuthority("VIEW_PROJECTS")
                        .requestMatchers(HttpMethod.POST, PROJECT_URL).hasAuthority("EDIT_PROJECTS")
                        .requestMatchers(HttpMethod.PATCH, PROJECT_URL).hasAuthority("EDIT_PROJECTS")
                        .requestMatchers(HttpMethod.PUT, PROJECT_URL).hasAuthority("EDIT_PROJECTS")
                        .requestMatchers(HttpMethod.DELETE, PROJECT_URL).hasAuthority("EDIT_PROJECTS")
                        .requestMatchers(HttpMethod.GET, DEPARTMENT_URL).hasAuthority("VIEW_DEPARTMENTS")
                        .requestMatchers(HttpMethod.POST, DEPARTMENT_URL).hasAuthority("EDIT_DEPARTMENTS")
                        .requestMatchers(HttpMethod.PATCH, DEPARTMENT_URL).hasAuthority("EDIT_DEPARTMENTS")
                        .requestMatchers(HttpMethod.PUT, DEPARTMENT_URL).hasAuthority("EDIT_DEPARTMENTS")
                        .requestMatchers(HttpMethod.DELETE, DEPARTMENT_URL).hasAuthority("EDIT_DEPARTMENTS")
                        .requestMatchers(HttpMethod.GET, ORGANIZATION_URL).hasAuthority("VIEW_ORGANIZATIONS")
                        .requestMatchers(HttpMethod.POST, ORGANIZATION_URL).hasAuthority("EDIT_ORGANIZATIONS")
                        .requestMatchers(HttpMethod.PATCH, ORGANIZATION_URL).hasAuthority("EDIT_ORGANIZATIONS")
                        .requestMatchers(HttpMethod.PUT, ORGANIZATION_URL).hasAuthority("EDIT_ORGANIZATIONS")
                        .requestMatchers(HttpMethod.DELETE, ORGANIZATION_URL).hasAuthority("EDIT_ORGANIZATIONS")
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
