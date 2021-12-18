package com.retoCinco.repository.crud;

import com.retoCinco.model.Order;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author Alberto
 */
public interface OrderCrudRepository extends MongoRepository<Order, Integer> {
    
    @Query("{'salesMan.zone': ?0}")
    List<Order> findByZone(final String zone);
    
    @Query("{status: ?0}")
    List<Order> findByStatus(final String status);
    
    Optional<Order> findTopByOrderByIdDesc();
}
