package net.winnings.controller;

import net.winnings.model.Bet;
import net.winnings.model.User;
import net.winnings.repository.BetRepository;
import net.winnings.repository.SportMatchRepository;
import net.winnings.repository.UserRepository;
import net.winnings.view.BetForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User profile controller class
 * @author Nosolenko
 * @version 1.0
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/")
public class ProfileController {
    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /** Bet entity repository for working with the database */
    @Autowired
    private BetRepository betRepository;

    /** SportMatch entity repository for working with the database */
    @Autowired
    private SportMatchRepository sportMatchRepository;

    /**
     * Function for processing a get request at the address "/userBets"
     * @return returns a list of bid views for the user
     */
    @GetMapping("/userBets")
    public List<BetForUser> getUserBets() {
        try{
            List<Bet> bets = betRepository.findAllByUserId(MainController.getLoggedUser().getId());
            ArrayList<BetForUser> fullBetList = new ArrayList<>();
            float coef = 0;
            for (Bet bet : bets) {
                if(bet.getTeamNumber() == 1) {
                    coef = sportMatchRepository.findById(bet.getMatchId()).get().getFirstCoefficient();
                }
                else{
                    coef = sportMatchRepository.findById(bet.getMatchId()).get().getSecondCoefficient();
                }
                fullBetList.add(new BetForUser(
                        bet.getMatchId(),
                        bet.getId(),
                        sportMatchRepository.findById(bet.getMatchId()).get().getName(),
                        bet.getTeamNumber(),
                        sportMatchRepository.findById(bet.getMatchId()).get().getDateOfMatch(),
                        coef,
                        bet.getMoney(),
                        bet.getWinResult()
                ));
            }
            return fullBetList;
        } catch(Exception ex) {
            return null;
        }
    }

    /**
     * Function for handling a put request at "/getGift"
     */
    @PutMapping("/getGift")
    public void getGift() {
        MainController.getLoggedUser().setMoneyScore(
                MainController.getLoggedUser().getMoneyScore() + 300
        );
        userRepository.save(MainController.getLoggedUser());
    }

    /**
     * Post request processing function at "/changePhoto"
     * @param image - the photo
     * @return returns the result of updating the photo
     */
    @PutMapping("/changePhoto")
    public ResponseEntity<String> changePhoto(@RequestBody MultipartFile image) {
        if (image != null) {
            String dir = System.getProperty("user.dir"); //путь к проекту
            String saveLocation = dir + "\\react-frontend\\public\\view-user\\images\\usersPic\\";
            String fileName = UUID.randomUUID().toString() + image.getOriginalFilename();
            String location = saveLocation;
            File pathFile = new File(location);
            pathFile = new File(location + fileName);
            try {
                image.transferTo(pathFile);
            } catch (IOException e) {
                return ResponseEntity.ok("Ошибка сохранения фотографии");
            }
            User user = MainController.getLoggedUser();
            user.setImage("usersPic/" + fileName);
            userRepository.save(user);
            MainController.setLoggedUser(user);
        }
        else {
            return ResponseEntity.ok("Ошибка загрузки");
        }
        return ResponseEntity.ok("Успешная загрузка");
    }
}
