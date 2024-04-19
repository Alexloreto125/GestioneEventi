package AlexSpring.GestioneEventi.controllers;

import AlexSpring.GestioneEventi.entities.Events;
import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.exceptions.BadRequestException;
import AlexSpring.GestioneEventi.payloads.NewEventDTO;
import AlexSpring.GestioneEventi.payloads.NewEventRespDTO;
import AlexSpring.GestioneEventi.services.AuthService;
import AlexSpring.GestioneEventi.services.EventService;
import AlexSpring.GestioneEventi.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/organizator")
public class OrganizatorController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private EventService eventService;


    @Transactional
    public User getUserWithPartecipazioni(UUID userId) {
        User user = authService.findById(userId);
        user.getPartecipazioni().size();
        return user;
    }


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
    @GetMapping("/users")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "surname") String sortBy){
        return this.userService.getUsers(page,size,sortBy);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public User findById(@PathVariable UUID userId){
        return this.authService.findById(userId);
    }

    //! ORGANIZZAOTRE
    //* CREAZIONI EVENTI
    //* PUO' VEDERE TUTTI GLI EVENTI
    //* PUO' VEDERE GLI EVENTI BY ID
    //* PUO' ELMINARE GLI EVENTI BY ID


    @PostMapping("/event")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public NewEventRespDTO newEvent(@RequestBody @Validated NewEventDTO payload, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewEventRespDTO(this.eventService.save(payload).getId());
    }

    @GetMapping("/event")
    public Page<Events> getAllEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "titoloEvento") String sortBy){
        return this.eventService.getAllEvents(page,size,sortBy);
    }
    @GetMapping("/event/{eventId}")
    public Events findById(@PathVariable Long eventId){
        return this.eventService.findById(eventId);
    }

    @DeleteMapping("/event/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvento(@PathVariable Long eventoId){
        this.eventService.delete(eventoId);
    }

//! ORGANIZZAOTRE
    //* PUO' AGGIUNGERE GLI UTENTI AGLI EVENTI
    //* PUO' RIMUOVERE GLI UTENTI DAGLI EVENTI


}
