package pl.coderslab.biblioteka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.biblioteka.model.Role;
import pl.coderslab.biblioteka.model.User;
import pl.coderslab.biblioteka.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

/*    public User save(User user) {
        // Hasło szyfrujemy przed zapisem
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }*/
    public User registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(Role.ROLE_USER); // domyślna rola
    return userRepository.save(user);
}

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
