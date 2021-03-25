package lt.akademija.itacademymanager.security;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Role;
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
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                cors().and().authorizeRequests()
                .antMatchers("/**").permitAll()
                //.antMatchers("/api/streams").hasAnyRole(Role.LECTURER.getRole(), Role.ADMIN.getRole(), Role.MANAGER.getRole())
                //.antMatchers("/api/students").hasAnyRole(Role.LECTURER.getRole(), Role.ADMIN.getRole(), Role.MANAGER.getRole())
                //.antMatchers("/api/users").hasRole(Role.ADMIN.getRole())
                .and().httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder.encoder());
        return authProvider;
    }
}


