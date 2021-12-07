package com.test.controller;

import com.test.exception.BadRequestException;
import com.test.exception.EmailAlreadyExistException;
import com.test.exception.NotFoundException;
import com.test.exception.NotVerifiedUserException;
import com.test.model.Address;
import com.test.model.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public List<User> getAll(){
        return userService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public User getById(@PathVariable int id) throws NotFoundException {
        return userService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getByEmail")
    public User getByEmail(@RequestParam("email") String email) throws NotFoundException {
        return userService.getByEmail(email);
    }

    @GetMapping("/getAllByName")
    public List<User> getAllByName(@RequestParam("name") String name){
        return userService.getAllByName(name);
    }


    @DeleteMapping("/remove/{id}")
    public void removeById(@PathVariable int id) {
        userService.removeById(id);
    }

    @PutMapping("/update")
    public User update(User user) throws NotFoundException {
        return userService.update(user);
    }

    @GetMapping("/usersInAddress")
    public List<User> getAllByAddress(@RequestBody Address address) {
        return userService.getAllByAddress(address);
    }

    @GetMapping("/login")
    public User login(@RequestParam("email") String email, @RequestParam("password") String password) throws NotFoundException, NotVerifiedUserException, BadRequestException {
        return userService.getByEmailAndPassword(email, password);
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) throws EmailAlreadyExistException, NotFoundException {
        User exisingUser = userService.getByEmail(user.getEmail());
        if (exisingUser != null)
            throw new EmailAlreadyExistException("There is user registered by this email!");
        userService.save(user);
    }

    @GetMapping("/verify")
    public void verify(@RequestParam String email){
        userService.verify(email);
    }

    @GetMapping("/resetPassword/Step1")
    public void resetPassword(@RequestParam String email) throws NotFoundException {
        User user = userService.upToResetPassword(email);
        //TODO and redirects to the page where the user can input the token which sent to user's email
    }


}
