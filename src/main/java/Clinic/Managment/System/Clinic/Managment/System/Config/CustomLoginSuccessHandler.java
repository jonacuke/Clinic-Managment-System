package Clinic.Managment.System.Clinic.Managment.System.Config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String redirectURL = "/dashboard";

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            if (role.equals("ROLE_ADMIN")) {
                redirectURL = "/admin-dashboard";
                break;
            } else if (role.equals("ROLE_DOCTOR")) {
                redirectURL = "/doctor-dashboard";
                break;
            } else if (role.equals("ROLE_PATIENT")) {
                redirectURL = "/patient-dashboard";
                break;
            }
        }

        response.sendRedirect(redirectURL);
    }
}
