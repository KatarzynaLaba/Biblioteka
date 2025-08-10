package pl.coderslab.biblioteka.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.coderslab.biblioteka.model.Role;
import pl.coderslab.biblioteka.model.User;
import pl.coderslab.biblioteka.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // hasło zakodowane
                admin.setEmail("admin@example.com");
                admin.setRole(Role.ROLE_ADMIN); // nadajemy rolę admina
                userRepository.save(admin);
                System.out.println("Utworzono konto admina: admin/admin123");
            }
        };
    }
}
