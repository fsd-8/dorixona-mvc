package net.idrok.dorixona.config;

import net.idrok.dorixona.security.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private  SecUserService secUserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll() // asset files
                .antMatchers("/").permitAll() // homepage
                .antMatchers("/login").permitAll() // login page
                .antMatchers("/pages/bino").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/pages/xona").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated() // others blocked
                .and()

                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login?error=true")
                .and()

                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(secUserService);
        provider.setPasswordEncoder(passwordEncoder());
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);

    }

    @Bean()
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
