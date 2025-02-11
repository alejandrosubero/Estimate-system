package com.jshandyman.service.security;

import com.jshandyman.service.security.auth.AplicationUserService;
import com.jshandyman.service.security.jwt.JwtConfig;
import com.jshandyman.service.security.jwt.JwtTokenVerifier;
import com.jshandyman.service.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;


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
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.crypto.SecretKey;

import static com.jshandyman.service.security.AplicationUserRole.*;
import static org.springframework.http.HttpMethod.GET;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AplicationUserService aplicationUserService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    public AplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                    AplicationUserService aplicationUserService,
                                    JwtConfig jwtConfig,
                                    SecretKey secretKey) {
        this.passwordEncoder = passwordEncoder;
        this.aplicationUserService = aplicationUserService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    
    @SuppressWarnings("deprecation")
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");

            }           

        };          
    }
    
 
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/AuthoritiesCotroller/**").permitAll()
                .antMatchers("/login","/logout").permitAll()
                .antMatchers("/","/start", "index", "/css/*,/js/*", "/new/user/**", "/new/user/start", "/template/new/**").permitAll()
                .antMatchers("/api/**").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers("/config/**").hasAnyRole(ADMIN.name(), ITADMIN.name())
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
        provider.setUserDetailsService(aplicationUserService);
        return provider;
    }

}
