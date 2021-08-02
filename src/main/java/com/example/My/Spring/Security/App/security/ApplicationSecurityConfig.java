package com.example.My.Spring.Security.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;

@Controller
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordConfig passwordConfig;

    @Autowired
    public void setPasswordConfig(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasAnyRole(ApplicationUserRoles.STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails AdminUser = User.builder()
                .username("janith")
                .password(passwordConfig.passwordEncoder().encode("janith123"))
             //  .roles(ApplicationUserRoles.ADMIN.name())
                .authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails ManagerUser = User.builder()
                .username("alan")
                .password(passwordConfig.passwordEncoder().encode("alan123"))
                //.roles(ApplicationUserRoles.STUDENT.name())
                .authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities())
                .build();
        UserDetails AdminTraineeUser = User.builder()
                .username("tom")
                .password(passwordConfig.passwordEncoder().encode("tom123"))
                //.roles(ApplicationUserRoles.ADMINTRAINEE.name())
                .authorities(ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                AdminUser,
                ManagerUser,
                AdminTraineeUser
        );
    }
}
