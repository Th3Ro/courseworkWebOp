package net.winnings.controller;

import net.winnings.model.Bet;
import net.winnings.repository.BetRepository;
import net.winnings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Rate controller class
 * @author Nosolenko
 * @version 1.0
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class BetController {
    /** Bet entity repository for working with the database */
    @Autowired
    private BetRepository betRepository;

    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /**
     * Function for processing a post request at "/newBet"
     * @param bet - bet
     * @return returns a new bet object
     */
    @PostMapping("/newBet")
    public Bet doBet(@RequestBody Bet bet) {
        Bet newBet = new Bet(
                bet.getMatchId(),
                bet.getUserId(),
                bet.getTeamNumber(),
                bet.getMoney()
        );
        betRepository.save(newBet);
        MainController.getLoggedUser().setMoneyScore(
                MainController.getLoggedUser().getMoneyScore() - bet.getMoney()
        );
        userRepository.save(MainController.getLoggedUser());
        return newBet;
    }
}
