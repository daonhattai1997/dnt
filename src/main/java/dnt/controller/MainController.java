package dnt.controller;

import dnt.exception.ResourceNotFoundException;
import dnt.repository.RoomRepository;
import dnt.repository.RoomTypeRepository;
import dnt.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

//    @Value("${welcome.message}")
//    private String message;
    @Autowired
    private RoomService room;

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public void welcome() {

                //.orElseThrow(() -> new ResourceNotFoundException("Room Type", "count", -1));
    }
}
