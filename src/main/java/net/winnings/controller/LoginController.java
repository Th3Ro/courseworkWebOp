package net.winnings.controller;

import net.winnings.exception.ResourceNotFoundException;
import net.winnings.model.User;
import net.winnings.model.UserLoginData;
import net.winnings.repository.UserLoginDataRepository;
import net.winnings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authorization controller class
 * @author Nosolenko
 * @version 1.0
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/")
public class LoginController {
    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /** Repository of the UserLoginData entity for working with the database */
    @Autowired
    private UserLoginDataRepository userLoginDataRepository;

    /**
     * Function for handling the get request at "/logout"
     */
    @GetMapping("/logout")
    public void logoutUser() {
        MainController.setUserIsAuth(false);
        MainController.setLoggedUser(null);
    }

    /**
     * Function for processing a post request at the address "/login/{login}"
     * @param login - user name
     * @param password - user password
     * @return returns authorization result
     */
    @PostMapping("/login/{login}")
    public ResponseEntity<String> loginUser(@PathVariable String login, @RequestBody String password) {
        String correctPassword = password.substring(0, password.length()-1);
        UserLoginData userLoginData = null;
        try{
            userLoginData = userLoginDataRepository.findByUserLogin(login);
            if(userLoginData.getUserPassword().equals(correctPassword)){
                MainController.setUserIsAuth(true);
                MainController.setLoggedUser(userRepository.findById(userLoginData.getUserId()).get());
                return ResponseEntity.ok("Успешная авторизация");
            }
            else{
                return ResponseEntity.ok("Не верный пароль");
            }
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.ok("Такого пользователя не существует");
        } catch (Exception ex){
            return ResponseEntity.ok("Такого пользователя не существует");
        }
    }

    /**
     * Function for getting the user authorization state
     * @return returns the result of user authorization
     */
    @GetMapping("/authUser")
    public User getAuthorizeUser() {
        return MainController.getLoggedUser();
    }
}
