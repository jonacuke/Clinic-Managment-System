package Clinic.Managment.System.Clinic.Managment.System.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {
    @GetMapping("/doctor-dashboard")
    public String showDashboard() {
        return "doctor-dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
