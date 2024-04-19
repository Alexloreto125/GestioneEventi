package AlexSpring.GestioneEventi.controllers;

import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.exceptions.BadRequestException;
import AlexSpring.GestioneEventi.payloads.NewUserDTO;
import AlexSpring.GestioneEventi.payloads.NewUserRespDTO;
import AlexSpring.GestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public NewUserRespDTO createUser(@RequestBody @Validated NewUserDTO payload, BindingResult validation){

        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }

        return new NewUserRespDTO(this.userService.save(payload).getId());

    }



}
