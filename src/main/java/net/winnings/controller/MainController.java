package net.winnings.controller;

import net.winnings.exception.ResourceNotFoundException;
import net.winnings.model.SportMatch;
import net.winnings.model.User;
import net.winnings.repository.SportMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Master controller class
 * @author Nosolenko
 * @version 1.0
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class MainController {
    /** Variable that stores the user's authorization state */
    private static boolean userIsAuth = false;

    /**
     * Field value retrieval function {@link MainController#userIsAuth}
     * @return returns the authorization status of the user
     */
    public static boolean isUserIsAuth() {
        return userIsAuth;
    }

    /**
     * Procedure for determining the user's authorization state {@link MainController#userIsAuth}
     * @param userIsAuth - user authorization state
     */
    public static void setUserIsAuth(boolean userIsAuth) {
        MainController.userIsAuth = userIsAuth;
    }

    /** Variable that stores the object of the authorized user */
    private static User loggedUser;

    /**
     * Field value retrieval function {@link MainController#loggedUser}
     * @return returns the authorized user object
     */
    public static User getLoggedUser() {
        return loggedUser;
    }

    /**
     * Procedure for determining the user's authorization state {@link MainController#loggedUser}
     * @param loggedUser - authorized user object
     */
    public static void setLoggedUser(User loggedUser) {
        MainController.loggedUser = loggedUser;
    }

    /** SportMatch entity repository for working with the database */
    @Autowired
    private SportMatchRepository sportMatchRepository;

    /**
     * Function for processing a get request at the address "/main"
     * @return returns a list of all matches
     */
    @GetMapping("/main")
    public List<SportMatch> getMatches() {
        return this.sportMatchRepository.findAll();
    }

    /**
     * Function for processing a get request at the address "/sportMatch/{id}"
     * @param id - bet identifier
     * @return returns the result of query execution
     */
    @GetMapping("/sportMatch/{id}")
    public ResponseEntity<SportMatch> getMatchById(@PathVariable Long id) {
        SportMatch sportMatch = sportMatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Матча не существует с id :" + id));
        return ResponseEntity.ok(sportMatch);
    }
}
