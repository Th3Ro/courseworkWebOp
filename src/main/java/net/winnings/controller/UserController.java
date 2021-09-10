package net.winnings.controller;

import net.winnings.exception.ResourceNotFoundException;
import net.winnings.model.User;
import net.winnings.model.UserLoginData;
import net.winnings.repository.UserLoginDataRepository;
import net.winnings.repository.UserRepository;
import net.winnings.view.RegisteringUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * User controller class
 * @author Nosolenko
 * @version 1.0
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/")
public class UserController {
    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /** Repository of the UserLoginData entity for working with the database */
    @Autowired
    private UserLoginDataRepository userLoginDataRepository;

    /**
     * Function for processing a post request at "/users"
     * @param registeringUser - registration data
     * @return returns a new user object
     */
    @PostMapping("/users")
    public User registerUser(@RequestBody RegisteringUser registeringUser) {
        User newUser = new User(
                registeringUser.getSurname(),
                registeringUser.getName(),
                registeringUser.getDateOfBirth()
        );
        userRepository.save(newUser);
        UserLoginData newUserLoginData = new UserLoginData(
                registeringUser.getUserLogin(),
                registeringUser.getUserPassword(),
                newUser.getId()
        );
        userLoginDataRepository.save(newUserLoginData);
        return newUser;
    }

    /**
     * Function for processing a get request at the address "/users/{id}"
     * @param id - user id
     * @return returns the result of query execution
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User employee = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователя не существует с id :" + id));
        return ResponseEntity.ok(employee);
    }

    /**
     * Function for processing a put request at "/users/{id}"
     * @param id - user id
     * @param userDetails - data to change
     * @return returns the result of query execution
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователя не существует с id :" + id));
        user.setSurname(userDetails.getSurname());
        user.setName(userDetails.getName());
        user.setDateOfBirth(userDetails.getDateOfBirth());
        user.setMoneyScore(userDetails.getMoneyScore());
        user.setImage(userDetails.getImage());
        MainController.setLoggedUser(user);
        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Function for processing a delete request at the address "/users/{id}"
     * @param id - user id
     * @return returns the result of query execution
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователя не существует с id :" + id));
        UserLoginData loginData = userLoginDataRepository.findByUserId(user.getId());
        // удаление пользователя
        userRepository.delete(user);
        // и его регистрационных данных
        userLoginDataRepository.delete(loginData);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}