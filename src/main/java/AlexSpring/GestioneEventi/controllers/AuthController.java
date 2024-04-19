package AlexSpring.GestioneEventi.controllers;

import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.exceptions.BadRequestException;
import AlexSpring.GestioneEventi.payloads.NewUserDTO;
import AlexSpring.GestioneEventi.payloads.NewUserRespDTO;
import AlexSpring.GestioneEventi.payloads.UserLoginDTO;
import AlexSpring.GestioneEventi.payloads.UserLoginRespDTO;
import AlexSpring.GestioneEventi.services.AuthService;
import AlexSpring.GestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/register")
    public NewUserRespDTO createUser(@RequestBody @Validated NewUserDTO payload, BindingResult validation){

        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }

        return new NewUserRespDTO(this.userService.save(payload).getId());

    }

    //* LOGIN CON PAYLOAD USERLOGINDTO E RESP CON ACCESSTOKEN
    @PostMapping("/login")
    public UserLoginRespDTO login(@RequestBody UserLoginDTO userLoginDTO){
        return new UserLoginRespDTO(authService.generateToke(userLoginDTO));

    }


}
