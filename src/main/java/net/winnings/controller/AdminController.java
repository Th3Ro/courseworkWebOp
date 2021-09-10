package net.winnings.controller;

import net.winnings.exception.ResourceNotFoundException;
import net.winnings.model.Bet;
import net.winnings.model.SportMatch;
import net.winnings.model.User;
import net.winnings.repository.BetRepository;
import net.winnings.repository.SportMatchRepository;
import net.winnings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Admin panel controller class
 * @author Nosolenko
 * @version 1.0
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class AdminController {
    /** SportMatch entity repository for working with the database */
    @Autowired
    private SportMatchRepository sportMatchRepository;

    /** Bet entity repository for working with the database */
    @Autowired
    private BetRepository betRepository;

    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /**
     * Function for processing a get request at the address "/sportMatches"
     * @return returns a list of sports matches
     */
    @GetMapping("/sportMatches")
    public List<SportMatch> getMatches() {
        return sportMatchRepository.findAll();
    }

    /**
     * Post request processing function at "/sportMatches"
     * @param sportMatch - sports match
     * @return returns a new user object
     */
    @PostMapping("/sportMatches")
    public SportMatch addMatch(@RequestBody SportMatch sportMatch) {
        SportMatch newSportMatch = new SportMatch(
                sportMatch.getName(),
                sportMatch.getDateOfMatch(),
                sportMatch.getFirstCoefficient(),
                sportMatch.getSecondCoefficient()
        );
        sportMatchRepository.save(newSportMatch);
        return newSportMatch;
    }

    /**
     * Function for handling put request at "/sportMatches/{id}"
     * @param id - match id
     * @param sportMatch - match
     * @return returns the result of query execution
     */
    @PutMapping("/sportMatches/{id}")
    public ResponseEntity<SportMatch> updateMatch(@PathVariable Long id, @RequestBody SportMatch sportMatch) {
        SportMatch newsportMatch = sportMatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Матча не существует с id :" + id));
        newsportMatch.setWinner(sportMatch.getWinner());
        newsportMatch.setIsEnd(true);
        SportMatch updatedSportMatch = sportMatchRepository.save(newsportMatch);
        List<Bet> bets = betRepository.findAllByMatchId(id);
        for(Bet bet : bets){
            if(bet.getTeamNumber() == sportMatch.getWinner()){
                bet.setWinResult((byte) 1);
                User user = userRepository.findById(bet.getUserId()).get();
                if (sportMatch.getWinner() == 1){
                    user.setMoneyScore(user.getMoneyScore() + (bet.getMoney() *
                            sportMatchRepository.findById(bet.getMatchId()).get().getFirstCoefficient()));
                }
                else{
                    user.setMoneyScore(user.getMoneyScore() + (bet.getMoney() *
                            sportMatchRepository.findById(bet.getMatchId()).get().getSecondCoefficient()));
                }
                if (user.getId() == MainController.getLoggedUser().getId()){
                    MainController.getLoggedUser().setMoneyScore(user.getMoneyScore());
                }
                userRepository.save(user);
            }
            else{
                bet.setWinResult((byte) 2);
            }
            betRepository.save(bet);
        }
        return ResponseEntity.ok(updatedSportMatch);
    }
}
