package com.retoCinco.repository.crud;


import com.retoCinco.model.HairProducts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 *
 * @author Alberto
 */
public interface HairProductsCrudRepository extends MongoRepository<HairProducts, String> {
    
    public List<HairProducts> findByPriceLessThanEqual(double precio);

    @Query("{'description':{'$regex':'?0','$options':'i'}}")
    public List<HairProducts> findByDescriptionLike(String description);
}

