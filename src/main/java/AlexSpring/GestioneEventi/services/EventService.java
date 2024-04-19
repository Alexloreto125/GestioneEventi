package AlexSpring.GestioneEventi.services;

import AlexSpring.GestioneEventi.entities.Events;
import AlexSpring.GestioneEventi.exceptions.BadRequestException;
import AlexSpring.GestioneEventi.exceptions.NotFoundException;
import AlexSpring.GestioneEventi.payloads.NewEventDTO;
import AlexSpring.GestioneEventi.repositories.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventDAO eventDAO;

    public Events findById(Long eventId) {
        return this.eventDAO.findById(eventId).orElseThrow(() -> new NotFoundException("L'evento con id"+ eventId+ " non è stato trovato"));


    }

    public Page<Events> getAllEvents(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.eventDAO.findAll(pageable);

    }


    public Events save(NewEventDTO body) {
        Optional<Events> existingEvent = eventDAO.findFirstByTitoloEvento(body.titoloEvento());
        if (existingEvent.isPresent() && existingEvent.get().getTitoloEvento().equals(body.titoloEvento())) throw new BadRequestException("Evento con lo stesso titolo già esistente");


        Events event = new Events(body.titoloEvento(), body.tipologia_evento(), body.date(), body.ubicazione(), body.max_partecipanti());
        return this.eventDAO.save(event);
    }


    public Events updateEvent(Long userId, Events eventUpdate) {
        Events found = this.findById(userId);

        if (eventUpdate.getTitoloEvento() != null) {
            found.setTitoloEvento(eventUpdate.getTitoloEvento());

        }
        if (eventUpdate.getTipologia_evento() != null) {
            found.setTipologia_evento(eventUpdate.getTipologia_evento());
        }
        if (eventUpdate.getDate() != null) {
            found.setDate(eventUpdate.getDate());
        }
        if (eventUpdate.getUbicazione() != null) {
            found.setUbicazione(eventUpdate.getUbicazione());
        }
        if (eventUpdate.getMax_partecipanti() != null) {
            found.setMax_partecipanti(eventUpdate.getMax_partecipanti());
        }
        return  this.eventDAO.save(found);
    }

    public void delete(Long id) {
        Events found = this.eventDAO.findById(id).orElseThrow(()-> new NotFoundException("L'evento con id"+ id+ " non è stato trovato"));
        this.eventDAO.delete(found);
    }



}
