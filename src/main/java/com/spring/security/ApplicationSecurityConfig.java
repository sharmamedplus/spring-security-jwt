package com.spring.security;

import com.spring.auth.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.spring.security.ApplicationUserRole.ADMIN;
import static com.spring.security.ApplicationUserRole.ADMIN_TRAINEE;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
// In case we are using method based annotation like @PreAuthorize commenting below code
//                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                //.antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annasmith = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
                //.roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails linda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password"))
                //.roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password"))
                //.roles(ADMIN_TRAINEE.name())
                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(annasmith, linda, tom);
        // Here InMemoryUserDetailsManager is one of implementation of UserDetailService
    }*/
}
