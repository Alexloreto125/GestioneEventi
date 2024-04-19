package AlexSpring.GestioneEventi.controllers;

import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.payloads.UserLoginDTO;
import AlexSpring.GestioneEventi.payloads.UserLoginRespDTO;
import AlexSpring.GestioneEventi.services.AuthService;
import AlexSpring.GestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@RequestParam(defaultValue = "surname") String sortBy){
    return this.userService.getUsers(page,size,sortBy);
    }



    //* LOGIN CON PAYLOAD USERLOGINDTO E RESP CON ACCESSTOKEN
    @PostMapping("/login")
    public UserLoginRespDTO login(@RequestBody UserLoginDTO userLoginDTO){
        return new UserLoginRespDTO(authService.generateToke(userLoginDTO));

    }




    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId){
        return this.authService.findById(userId);
    }
}
