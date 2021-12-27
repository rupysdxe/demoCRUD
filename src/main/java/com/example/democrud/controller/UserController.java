package com.example.democrud.controller;
import com.example.democrud.model.User;
import com.example.democrud.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    //get all users api
    @GetMapping("/getAll")
    public List<User> getAllUser(){
        return this.userRepo.findAll();
    }
    // get user by id
    @GetMapping(value ="/{id}")
    public User getById(@PathVariable("id") Long userID){
        return this.userRepo.findById(userID).get();
    }
    //create
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepo.save(user);
    }

    //update
        @PutMapping(value = "/{id}")
        public  User updateUser(@RequestBody User user,@PathVariable("id") Long userId){
           User existingUser=getById(userId);
           existingUser.setFirstName(user.getFirstName());
           existingUser.setLastName(user.getLastName());
           existingUser.setEmail(user.getEmail());
           return this.userRepo.save(existingUser);
        }

    //delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id")Long userId){
        this.userRepo.deleteById(userId);
        return ResponseEntity.ok().build();
    }


}
