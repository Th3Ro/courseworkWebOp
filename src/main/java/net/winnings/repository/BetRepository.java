package net.winnings.repository;

import net.winnings.model.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for interacting with the Bet table of the database
 * @author Nosolenko
 * @version 1.0
 */
@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {
    /**
     * Function for getting a list of bets like {@link Bet}
     * @param userId - user id
     * @return returns a list of rates
     */
    List<Bet> findAllByUserId(Long userId);

    /**
     * Function for getting a list of bets like {@link Bet}
     * @param matchId - match id
     * @return returns a list of rates
     */
    List<Bet> findAllByMatchId(Long matchId);
}
