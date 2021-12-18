package com.retoCinco.repository.crud;

import com.retoCinco.model.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Alberto
 */
public interface UserCrudRepository extends MongoRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
    
    Optional<User> findTopByOrderByIdDesc();

    List<User> findByMonthBirthtDay(String month);
}
