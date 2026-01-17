package Clinic.Managment.System.Clinic.Managment.System.Config;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.Role;
import Clinic.Managment.System.Clinic.Managment.System.Repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;

    public CustomAthenticationSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() == Role.DOCTOR) {
            response.sendRedirect("/doctor/dashboard");
        } else if (user.getRole() == Role.ADMIN) {
            response.sendRedirect("/admin/dashboard");
        } else if (user.getRole() == Role.RECEPTIONIST) {
            response.sendRedirect("/patient/dashboard");
        } else {
            response.sendRedirect("/dashboard");
        }
    }
}

