package com.alexahdp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.alexahdp.spring.database.entity.Role.ADMIN;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(
                urlConfig ->urlConfig
                    .antMatchers("/login", "/register", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .antMatchers("/users/{\\d+}/delete").hasAnyAuthority(ADMIN.getAuthority())
                    .antMatchers("/admin/**").hasAnyAuthority(ADMIN.getAuthority())
                    .anyRequest().authenticated()
            )
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
            )
//            .httpBasic(Customizer.withDefaults());
            .formLogin(login -> login
                    .loginPage("/login")
                    .defaultSuccessUrl("/users")
                    .permitAll()
            );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
