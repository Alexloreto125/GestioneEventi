package AlexSpring.GestioneEventi.controllers;

import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.services.AuthService;
import AlexSpring.GestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/organizator")
public class OrganizatorController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    //! ORGANIZZATORE COME USER
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

    //! ORGANIZZAOTRE

    //* PUO' VEDERE TUTTI GLI USERS
    //* PUO' VEDERE GLI USER BY ID
    //* PUO' MODIFICARE USER BY ID
    //* PUO' ELIMINARE USER BY ID

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public User findById(@PathVariable UUID userId){
        return this.authService.findById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody User body){
        return this.authService.updateUser(userId, body);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID userId){
        this.authService.findByIdAndDelete(userId);
    }



    //? AGGIUNGE GLI AVENTI??
}
