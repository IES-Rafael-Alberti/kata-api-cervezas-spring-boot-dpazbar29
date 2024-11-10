package org.example.cerveza.controllers;

import org.apache.coyote.Response;
import org.example.cerveza.entities.Beer;
import org.example.cerveza.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beer")
public class BeerController {
    @Autowired
    private BeerService beerService;

    @GetMapping("/beers")
    public List<Beer> getAllBeers() {
        return beerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beer> getBeerById(@PathVariable Long id) {
        return beerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Beer createBeer(@RequestBody Beer beer) {
        return beerService.save(beer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beer> updateBeer(@PathVariable Long id, @RequestBody Beer beer) {
        return ResponseEntity.ok(beerService.updateBeer(beer, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        beerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
