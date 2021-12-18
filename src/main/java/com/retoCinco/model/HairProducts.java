package com.retoCinco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidad Hair Products
 * @author Alberto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "hairproducts")
public class HairProducts {

    @Id
    private String reference;
    private String brand;
    private String category;
    private String name;
    private String description;
    private boolean availability = true;
    private double price;
    private int quantity;
    private String photography;
}

