package com.mdi.worknout.resources;


import com.mdi.worknout.models.Event;
import com.mdi.worknout.models.User;
import com.mdi.worknout.repositories.EventRepository;
import com.mdi.worknout.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventRepository.findAll(Sort.by(Sort.Direction.ASC,"date"));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id){
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event.get(), HttpStatus.OK);
    }

    @PostMapping("/events")
    public ResponseEntity<Event> postEvent(@Valid @RequestBody Event event) {
        Event savedEvent = eventRepository.save(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Delete event with ID = " + id + "...");
        eventRepository.deleteById(id);
        return new ResponseEntity<>("Event"+id+" has been deleted!", HttpStatus.OK);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id, @RequestBody Event event) {
        System.out.println("Update event with ID = " + id + "...");

        Optional<Event> oldEvent = eventRepository.findById(id);
        if (!oldEvent.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            Event UpdatedEvent = oldEvent.get();
            UpdatedEvent.setTitle(event.getTitle());
            UpdatedEvent.setDescription(event.getDescription());
            UpdatedEvent.setLocation(event.getLocation());
            UpdatedEvent.setDate(event.getDate());
            return new ResponseEntity<>(eventRepository.save(UpdatedEvent), HttpStatus.OK);
    }

    //ajout un user a un evenement
    @PutMapping("/events/{id}/vote")
    public ResponseEntity<Event> postUser(@PathVariable("id") long id, @Valid @RequestBody User user) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User savedUser = userRepository.save(user);
        List usersList = event.get().getListUsers();
        usersList.add(savedUser);
        Event UpdatedEvent = event.get();
        UpdatedEvent.setListUsers(usersList);
        return new ResponseEntity<>(eventRepository.save(UpdatedEvent), HttpStatus.OK);
    }

    //Liste les users d'un evenement
    @GetMapping("/events/{id}/users")
    public ResponseEntity<List<User>> getUsers(@PathVariable("id") long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List usersList = event.get().getListUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
