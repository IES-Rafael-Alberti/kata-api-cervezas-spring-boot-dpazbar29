package org.example.cerveza.controllers;

import org.apache.coyote.Response;
import org.example.cerveza.entities.Brewerie;
import org.example.cerveza.services.BrewerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brewerie")
public class BrewerieController {
    @Autowired
    private BrewerieService brewerieService;

    @GetMapping("/breweries")
    public List<Brewerie> getAllBreweries() {
        return brewerieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brewerie> getBrewerieById(@PathVariable Long id) {
        return brewerieService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Brewerie createBrewerie(@RequestBody Brewerie brewerie) {
        return brewerieService.save(brewerie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brewerie> updateBrewerie(@PathVariable Long id, @RequestBody Brewerie brewerie) {
        return ResponseEntity.ok(brewerieService.updateBrewerie(brewerie, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrewerie(@PathVariable Long id) {
        brewerieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
