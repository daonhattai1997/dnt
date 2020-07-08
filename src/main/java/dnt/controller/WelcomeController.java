package dnt.controller;

import dnt.exception.ResourceNotFoundException;
import dnt.repository.RoomRepository;
import dnt.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WelcomeController {

//    @Value("${welcome.message}")
//    private String message;

    @Autowired
    private RoomTypeRepository roomTp;
    @Autowired
    private RoomRepository room;

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public long welcome() {
        return roomTp.count();
                //.orElseThrow(() -> new ResourceNotFoundException("Room Type", "count", -1));
    }
}
