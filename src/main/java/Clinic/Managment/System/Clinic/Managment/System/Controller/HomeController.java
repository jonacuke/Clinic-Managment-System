package Clinic.Managment.System.Clinic.Managment.System.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String homepage(Model model){
        model.addAttribute("title","Clinic Managment System");
        return "home";
    }


}
