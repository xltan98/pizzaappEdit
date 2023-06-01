package sg.edu.nus.iss.pizzaapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.pizzaapp.model.Delivery;
import sg.edu.nus.iss.pizzaapp.model.Order;
import sg.edu.nus.iss.pizzaapp.model.Pizza;
import sg.edu.nus.iss.pizzaapp.service.PizzaService;

@Controller
@RequestMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class PizzaController {
    @Autowired
    private PizzaService pizzaSvc;

    @PostMapping(path="/pizza")
    public String postPizza(Model m , HttpSession sesssion, 
            @Valid Pizza pizza, BindingResult result){

        if(result.hasErrors()){
            return "index";
        }

        List<ObjectError> errors = pizzaSvc.validatePizzaOrder(pizza);
        if(!errors.isEmpty()){
            for(ObjectError e :errors)
                result.addError(e);
            return "index";
        }

        sesssion.setAttribute("pizza", pizza);
        m.addAttribute("delivery", new Delivery());
        return "delivery";
    }

    @PostMapping(path="/pizza/order")
    public String postPizzaOrder(Model model, HttpSession session,
        @Valid Delivery delivery, BindingResult result){

        if(result.hasErrors()){
            return "delivery";
        }
        Pizza p = (Pizza) session.getAttribute("pizza");
        Order o = pizzaSvc.savePizza(p, delivery);
        model.addAttribute("order", o);
        return "order";
    }
}
