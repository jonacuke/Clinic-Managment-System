package Clinic.Managment.System.Clinic.Managment.System;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.Role;
import Clinic.Managment.System.Clinic.Managment.System.Repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class ClinicManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicManagmentSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {

			if(userRepository.findByEmail("admin@clinic.com").isEmpty()){
				User admin = new User();

				admin.setUserName("Admin");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setRole(Role.ADMIN);
				admin.setEmail("admin@clinic.com");
				admin.setLastName("System");
				admin.setBirthday(LocalDate.of(1990, 1, 1));


				userRepository.save(admin);

				System.out.println("Admin account created: admin@clinic.com / admin123");
			}
		};
	}
}
