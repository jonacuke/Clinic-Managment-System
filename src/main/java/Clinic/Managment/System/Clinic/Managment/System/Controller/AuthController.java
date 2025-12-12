package Clinic.Managment.System.Clinic.Managment.System.Controller;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import Clinic.Managment.System.Clinic.Managment.System.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user,
                                  BindingResult result,
                                  Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        try {

            userService.userRegistration(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }


        model.addAttribute("successMessage", "Registration successful! Please log in.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return  "login";
    }
}





