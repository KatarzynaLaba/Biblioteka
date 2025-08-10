package pl.coderslab.biblioteka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

      @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // wyłączamy CSRF dla REST API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // rejestracja, logowanie dostępne dla wszystkich
                        .requestMatchers("/api/books/**").permitAll() // lista książek widoczna publicznie
                        //.requestMatchers("/api/users/register").permitAll() // rejestracja bez uwierzytelniania
                        .requestMatchers("/api/users/**").permitAll() // żeby udostępnić userów bez logowania
                        .requestMatchers("/api/loans/**").permitAll() // żeby udostępnić userów bez logowania
                        .requestMatchers("/api/authors/**").permitAll()
                        .anyRequest().authenticated() // reszta wymaga zalogowania
                )
                .formLogin(form -> form.permitAll()) // proste logowanie formularzem
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}

