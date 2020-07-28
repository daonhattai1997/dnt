package dnt.controller;

import dnt.config.JwtTokenProvider;
import dnt.entity.RoomType;
import dnt.exception.ResourceNotFoundException;
import dnt.service.AuthService;
import dnt.service.MenuService;
import dnt.service.RoomService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Setter(onMethod = @__(@Autowired))
public class MainController {

    private RoomService room;
    private MenuService menuService;

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
