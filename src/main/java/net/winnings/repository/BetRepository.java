package net.winnings.repository;

import net.winnings.model.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for interacting with the Bet table of the database
 * @author Nosolenko
 * @version 1.0
 */
@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {
}
