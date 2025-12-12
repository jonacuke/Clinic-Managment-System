
package Clinic.Managment.System.Clinic.Managment.System.Service;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.Role;
import Clinic.Managment.System.Clinic.Managment.System.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Metoda për regjistrimin e përdoruesit
    public User userRegistration(User user) {
        if (user.getBirthday() == null) {
            throw new IllegalArgumentException("Birthday nuk mund të jetë null");
        }

        int age = Period.between(user.getBirthday(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("Mosha duhet të jetë 18+");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(Role.DOCTOR);
        }

        return userRepository.save(user);
    }

    // Merr të gjithë përdoruesit
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Gjen përdoruesin sipas email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Përditësim i të dhënave të përdoruesit
    public void updateUser(User user) {
        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setUserName(user.getUserName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());

        // Vetëm ndrysho password nëse është dhënë
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        existing.setBirthday(user.getBirthday());
        existing.setRole(user.getRole());

        userRepository.save(existing);
    }
}
