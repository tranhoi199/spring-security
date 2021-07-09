package com.example.security.security;

import com.example.security.auth.ApplicationUserService;
import com.example.security.jwt.JwtTokenVerifier;
import com.example.security.jwt.JwtUsernameAndPasswordAuthenticateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//TODO
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticateFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticateFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .antMatchers("/api/**").hasRole(UserRoles.STUDENT.name())
//                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET ,"/management/api/**").hasAnyRole(UserRoles.ADMIN.name(), UserRoles.ADMINTRAINEE.name())
                .anyRequest()
                .authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }


}
