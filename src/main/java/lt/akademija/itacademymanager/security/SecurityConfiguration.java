package lt.akademija.itacademymanager.security;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.ApplicationUser;
import lt.akademija.itacademymanager.model.Role;
import lt.akademija.itacademymanager.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                cors().and().authorizeRequests()
                //.antMatchers("/**").permitAll()
                .antMatchers("/api/streams").hasAnyRole(Role.LECTURER.getRole(), Role.ADMIN.getRole(), Role.MANAGER.getRole())
                .antMatchers("/api/students").hasAnyRole(Role.LECTURER.getRole(), Role.ADMIN.getRole(), Role.MANAGER.getRole())
                .antMatchers("/api/users").hasRole(Role.ADMIN.getRole())
                .and().httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider());
        initializeAdmin();
    }

    private void initializeAdmin() {
        if (!userRepository.existsByEmail("admin@admin.com")) {
            ApplicationUser admin = new ApplicationUser( "admin admin", "admin@admin.com",passwordEncoder.encoder().encode("admin2admin123"), "ADMIN");
            userRepository.save(admin);
        }
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder.encoder());
        return authProvider;
    }
}


