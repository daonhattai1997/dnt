package dnt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping("/welcome")
    public String welcome(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("content", message + errorMessage);
        model.addAttribute("name", name);

        return "example";
    }
}
