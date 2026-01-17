package Clinic.Managment.System.Clinic.Managment.System.Controller;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Appointment;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Doctor;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import Clinic.Managment.System.Clinic.Managment.System.Service.AppointmentService;
import Clinic.Managment.System.Clinic.Managment.System.Service.DoctorService;
import Clinic.Managment.System.Clinic.Managment.System.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private final AppointmentService appointmentService;
    private final UserService userService;

    public DoctorController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user != null ? user : new User());

        final List<Appointment> appointments;
        final String errorMessage;

        boolean doctorExists = user != null && appointmentService.doctorProfileExists(user);

        if (!doctorExists) {
            appointments = Collections.emptyList();
            errorMessage = "Profili i doktorit nuk ekziston! Kontakto administratën.";
        } else {
            appointments = appointmentService.getAppointmentsByDoctor(user);
            errorMessage = null;
        }

        model.addAttribute("appointments", appointments);
        model.addAttribute("errorMessage", errorMessage);

        return "doctor-dashboard";
    }
}