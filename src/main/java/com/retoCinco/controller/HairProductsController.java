package com.retoCinco.controller;


import com.retoCinco.model.HairProducts;
import com.retoCinco.service.HairProductsService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador
 * @author Alberto
 */
@RestController
@RequestMapping("/api/hairproducts")
@CrossOrigin("*")
public class HairProductsController {

    @Autowired
    private HairProductsService hairProductsService;

    @GetMapping("/all")
    public List<HairProducts> getAll() {
        return hairProductsService.getAll();
    }

    @GetMapping("/{reference}")
    public Optional<HairProducts> getHairProducts(@PathVariable("reference") String reference) {
        return hairProductsService.getHairProducts(reference);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public HairProducts create(@RequestBody HairProducts gadget) {
        return hairProductsService.create(gadget);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public HairProducts update(@RequestBody HairProducts gadget) {
        return hairProductsService.update(gadget);
    }

    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("reference") String reference) {
        return hairProductsService.delete(reference);
    }
    

    @GetMapping("/price/{price}")
    public List<HairProducts> productsByPrice(@PathVariable("price") double precio){
        return hairProductsService.productsByPrice(precio);
    }

    @GetMapping("/description/{description}")
    public List<HairProducts> findByDescriptionLike(@PathVariable("description") String description){
        return hairProductsService.findByDescriptionLike(description);
    }
}
