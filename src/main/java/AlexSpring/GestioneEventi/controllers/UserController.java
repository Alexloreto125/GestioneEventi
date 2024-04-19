package AlexSpring.GestioneEventi.controllers;

import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.exceptions.BadRequestException;
import AlexSpring.GestioneEventi.payloads.NewUserDTO;
import AlexSpring.GestioneEventi.payloads.NewUserRespDTO;
import AlexSpring.GestioneEventi.services.AuthService;
import AlexSpring.GestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId){
        return this.authService.findById(userId);
    }

    @PutMapping("/me")
    //! ADESSO CHE ABBIAMO FATTO IL FILTER POSSIAMO VALIDARE IL TOKEN E ASSOCIARE L'UTENTE DEL DB
    public User updateUser(@AuthenticationPrincipal User userAuthenticated, @RequestBody User updateUser){
        return this.authService.updateUser(userAuthenticated.getId(),updateUser);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser){
        this.authService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }
    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser){

        return currentAuthenticatedUser;
    }




}
