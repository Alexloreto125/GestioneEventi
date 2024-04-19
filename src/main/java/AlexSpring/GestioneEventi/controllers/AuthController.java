package AlexSpring.GestioneEventi.controllers;

import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizator")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@RequestParam(defaultValue = "surname") String sortBy){
    return this.userService.getUsers(page,size,sortBy);
    }
}
