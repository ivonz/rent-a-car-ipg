package com.example.rentacaripg.web;

import com.example.rentacaripg.model.dtos.UserRegistrationDTO;
import com.example.rentacaripg.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {

    private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult";
    private static final String DOT = ".";

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {

        if (!model.containsAttribute("userRegistrationDTO")) {
            model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        }

        return new ModelAndView("auth-register");
    }
    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("userRegistrationDTO") UserRegistrationDTO userRegistrationDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegistrationDTO", userRegistrationDTO)
                    .addFlashAttribute(BINDING_RESULT_PATH + DOT + "userRegistrationDTO", bindingResult);
            modelAndView.setViewName("redirect:register");
        } else {
            this.userService.registerUser(userRegistrationDTO);
            modelAndView.setViewName("redirect:login");
        }

        return modelAndView;
    }
}
