package com.isep.appointement.controller.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.appointement.controller.doctor.DoctorService;
import com.isep.appointement.controller.patient.PatientService;
import com.isep.appointement.model.Doctor;
import com.isep.appointement.model.Patient;
import com.isep.appointement.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @Autowired
    @Qualifier("delegatedAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;


    @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider dauth = new DaoAuthenticationProvider();
        dauth.setUserDetailsService(patientService);
        dauth.setPasswordEncoder(passwordEncoder());
        return dauth;
    }

    @Bean
    public AuthenticationProvider doctorDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(doctorService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.authenticationProvider(doctorDaoAuthenticationProvider());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAt(dynamicAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/register**", "/static/**", "/static/assets**", "/static/assets/**", "/css/**", "/js/**", "/img/**", "/home**","/science","/announcement","/aboutus","/contact us", "/register/**").permitAll()
                .antMatchers("/patient**", "/patient/**","/doctor**","/doctor/**").hasAuthority("ADMIN")
                .antMatchers("/info/doctor**", "/info/doctor/**", "/appointment/doctor**", "/appointment/doctor/**").hasAnyAuthority("Doctor", "ADMIN")
                .antMatchers("/info/patient**", "/info/patient/**", "/appointment/patient**", "/appointment/patient/**").hasAnyAuthority("Patient", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new RestAccessDeniedHandler())
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login/doctor")
                .defaultSuccessUrl("/info/doctor")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

@Bean
public DynamicAuthenticationFilter dynamicAuthenticationFilter() throws Exception {
    DynamicAuthenticationFilter filter = new DynamicAuthenticationFilter(new AntPathRequestMatcher("/login", "POST"));
    filter.setAuthenticationManager(authenticationManagerBean());
    return filter;
}

    public class DynamicAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

        public DynamicAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
            super(requiresAuthenticationRequestMatcher);
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
            String autype = request.getParameter("autype");
            AuthenticationProvider authenticationProvider;
            if (autype.equals("doctor")) {
                authenticationProvider = doctorDaoAuthenticationProvider();
            } else {
                authenticationProvider = daoAuthenticationProvider();
            }

            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password"))
            );

            return authentication;
        }
    }
    @PostMapping(value = "/login-handler", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> loginWithExceptionHandler() {
        return ResponseEntity.ok(new RestResponse("Success"));
    }


    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }

}
