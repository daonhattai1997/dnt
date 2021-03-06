package dnt.controller;

import dnt.entity.RoomType;
import dnt.exception.ResourceNotFoundException;
import dnt.service.AuthService;
import dnt.service.MenuService;
import dnt.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @Autowired
    private MenuService menuService;

    @Autowired
    private AuthService authService;

    @GetMapping(value = {"/", "/home"})
    public String root() {
        return "home";
    }

    @GetMapping("/room")
    public List<RoomType> welcome() {
        return room.findAll();
                //.orElseThrow(() -> new ResourceNotFoundException("Room Type", "count", -1));
    }

    @GetMapping("/menu")
    public ResponseEntity menuView() {
        return ResponseEntity.ok(menuService.getMenuByRole(1));
    }

    @GetMapping("/menu-link")
    public ResponseEntity getUrl() {
        return ResponseEntity.ok(menuService.getMenuLink(1));
    }

}
