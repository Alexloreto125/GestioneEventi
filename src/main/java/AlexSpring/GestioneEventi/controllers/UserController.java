package AlexSpring.GestioneEventi.controllers;

import AlexSpring.GestioneEventi.entities.Events;
import AlexSpring.GestioneEventi.entities.Partecipazioni;
import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.exceptions.BadRequestException;
import AlexSpring.GestioneEventi.payloads.NewEventRespDTO;
import AlexSpring.GestioneEventi.payloads.PartecipazioneDTO;
import AlexSpring.GestioneEventi.payloads.PartecipazioneRespDTO;
import AlexSpring.GestioneEventi.services.AuthService;
import AlexSpring.GestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;


    //! USER
    //* PUO' VEDERE IL PROPRIO PROFILO
    //* PUO' MODIFICARE IL PROPRIO PROFILO
    //* PUO' CANCELLARE IL PROPRIO PROFILO


    @PutMapping("/me")
    //! ADESSO CHE ABBIAMO FATTO IL FILTER POSSIAMO VALIDARE IL TOKEN E ASSOCIARE L'UTENTE DEL DB
    public User updateUser(@AuthenticationPrincipal User userAuthenticated, @RequestBody User updateUser) {
        return this.authService.updateUser(userAuthenticated.getId(), updateUser);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.authService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {

        return currentAuthenticatedUser;
    }


    //! USER

    //* PUO' PRENOTARSI AD UN EVENTO


    @PostMapping("me/add/event")
    public PartecipazioneRespDTO addToEvenet(@AuthenticationPrincipal User currentUser, @RequestBody @Validated PartecipazioneDTO body) {
        Long eventId= body.id_evento();
        Partecipazioni partecipazioni= userService.addUserToEvent(currentUser,eventId);
        return new PartecipazioneRespDTO(partecipazioni.getId());
    }

}
