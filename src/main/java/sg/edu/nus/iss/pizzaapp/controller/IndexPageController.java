package sg.edu.nus.iss.pizzaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sg.edu.nus.iss.pizzaapp.model.Pizza;

@Controller
public class IndexPageController {
 
    @GetMapping(path="/")
    public String showLandingPage(Model m){
        m.addAttribute("pizza", new Pizza());
        return "index";
    }
}
