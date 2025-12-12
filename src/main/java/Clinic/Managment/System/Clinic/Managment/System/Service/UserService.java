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

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User userRegistration(User user) {
        int age = Period.between(user.getBirthday(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("Mosha duhet te jete 18+");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(Role.DOCTOR);
        }

        System.out.println("Saving user: " + user.getEmail());
        return userRepository.save(user);
    }



    public  List<User>getAllUsers(){
        return userRepository.findAll();
        }

        public Optional<User>findByEmail(String email){
            return userRepository.findByEmail(email);
        }

        public void updateUser(User user){
         User existing=userRepository.findById(user.getId())
                 .orElseThrow(()->new RuntimeException("User not found"));
                 existing.setUserName(existing.getUserName());
                 existing.setLastName(existing.getLastName());
                 existing.setEmail(existing.getEmail());
                 existing.setPassword(existing.getPassword());
                 existing.setBirthday(existing.getBirthday());
        }
    }

