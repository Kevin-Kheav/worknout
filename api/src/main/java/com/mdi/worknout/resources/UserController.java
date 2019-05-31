package com.mdi.worknout.resources;


import com.mdi.worknout.models.User;
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
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> postUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Delete user with ID = " + id + "...");
        userRepository.deleteById(id);
        return new ResponseEntity<>("User"+id+" has been deleted!", HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Update user with ID = " + id + "...");

        Optional<User> oldUser = userRepository.findById(id);
        if (!oldUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            User UpdatedUser = oldUser.get();
            UpdatedUser.setName(user.getName());
            UpdatedUser.setEmail(user.getEmail());
            return new ResponseEntity<>(userRepository.save(UpdatedUser), HttpStatus.OK);
        }
    }
}
