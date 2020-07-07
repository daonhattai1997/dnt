package dnt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping("/welcome")
    public String welcome(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("content", "haha");
        model.addAttribute("name", name);

        return "example";
    }
}
