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

    public User userRegistration(User user) {

        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("Username nuk mund të jetë bosh");
        }

        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last Name nuk mund të jetë bosh");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email nuk mund të jetë bosh");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password nuk mund të jetë bosh");
        }

        if (user.getBirthday() == null) {
            throw new IllegalArgumentException("Birthday nuk mund të jetë null");
        }

        int age = Period.between(user.getBirthday(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("Mosha duhet të jetë 18+");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(Role.PATIENT);
        }

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateUser(User user) {
         User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User me id " + user.getId() + " nuk ekziston"));

        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("Username nuk mund të jetë bosh");
        }

        existing.setUserName(user.getUserName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        existing.setBirthday(user.getBirthday());
        existing.setRole(user.getRole());

        userRepository.save(existing);
    }

}
