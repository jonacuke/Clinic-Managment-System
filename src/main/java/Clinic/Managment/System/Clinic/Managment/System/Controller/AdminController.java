package Clinic.Managment.System.Clinic.Managment.System.Controller;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Appointment;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Doctor;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Patient;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.Role;
import Clinic.Managment.System.Clinic.Managment.System.Repository.UserRepository;
import Clinic.Managment.System.Clinic.Managment.System.Service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    private final AdminService adminService;
    private final UserRepository userRepository;

    public AdminController(AdminService adminService,UserRepository userRepository) {
        this.adminService = adminService;
        this.userRepository=userRepository;

    }
    @GetMapping("/admin-dashboard")
    public String dashboard(Model model) {
        model.addAttribute("doctors", adminService.getAllDoctors());
        model.addAttribute("patients", adminService.getAllPatient());
        model.addAttribute("appointments", adminService.getAllAppointment());
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("patient", new Patient());
        model.addAttribute("appointment", new Appointment());
        return "admin-dashboard";
    }

    @GetMapping("/admin/add-doctor")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";
    }

    @GetMapping("/admin/delete-doctor/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        adminService.deleteDoctorById(id);
        return "redirect:/admin-dashboard";
    }
    @PostMapping("/admin/save-doctor")
    public String saveDoctor(@ModelAttribute Doctor doctor) {

        // Generate a unique username
        String baseUsername = doctor.getName().toLowerCase() + "." + doctor.getSurname().toLowerCase();
        String username = baseUsername;
        int counter = 1;

        while (userRepository.existsByUserName(username)) {
            username = baseUsername + counter; // add number if already exists
            counter++;
        }

        // Create a User for the doctor
        User user = new User();
        user.setUserName(username);
        user.setPassword(new BCryptPasswordEncoder().encode("default123")); // temporary password
        user.setRole(Role.DOCTOR);
        user.setEmail(username + "@clinic.com");

        userRepository.save(user);

        doctor.setUser(user);
        adminService.saveDoctor(doctor);

        return "redirect:/admin-dashboard";
    }


}
