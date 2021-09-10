package net.winnings.repository;

import net.winnings.model.SportMatch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for interacting with the SportMatch database table
 * @author Nosolenko
 * @version 1.0
 */
@Repository
public interface SportMatchRepository extends CrudRepository<SportMatch, Long> {
}
