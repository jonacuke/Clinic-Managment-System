package Clinic.Managment.System.Clinic.Managment.System.Controller;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Appointment;
import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.AppointmentStatus;
import Clinic.Managment.System.Clinic.Managment.System.Service.AppointmentService;
import Clinic.Managment.System.Clinic.Managment.System.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/doctor/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @GetMapping
    public String listAppointments(Model model, Principal principal) {
        var user = userService.findByUsername(principal.getName());
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(user);
        model.addAttribute("appointments", appointments);
        return "appointments-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("statuses", AppointmentStatus.values());
        return "appointments/new";
    }

    @PostMapping("/new")
    public String addAppointment(Principal principal, @ModelAttribute Appointment appointment) {
        var user = userService.findByUsername(principal.getName());
        appointmentService.addAppointment(user, appointment);
        return "redirect:/doctor/appointments";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, Principal principal) {
        var user = userService.findByUsername(principal.getName());
        Appointment appointment = appointmentService.getAppointmentsByDoctor(user).stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Takimi nuk ekziston"));
        model.addAttribute("appointment", appointment);
        model.addAttribute("statuses", AppointmentStatus.values());
        return "appointments/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateAppointmentStatus(@PathVariable Long id, @RequestParam String status, Principal principal) {
        var user = userService.findByUsername(principal.getName());
        appointmentService.updateAppointmentStatus(user, id, status);
        return "redirect:/doctor/appointments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable Long id, Principal principal) {
        var user = userService.findByUsername(principal.getName());
        appointmentService.deleteAppointment(user, id);
        return "redirect:/doctor/appointments";
    }
}


