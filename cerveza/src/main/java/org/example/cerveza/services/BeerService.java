package org.example.cerveza.services;

import org.example.cerveza.entities.Beer;
import org.example.cerveza.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {
    @Autowired
    private BeerRepository beerRepository;

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Optional<Beer> findById(Long id) {
        return beerRepository.findById(id);
    }

    public Beer save(Beer beer) {
        return beerRepository.save(beer);
    }

    public void deleteById(Long id) {
        beerRepository.deleteById(id);
    }

    public Beer updateBeer(Beer beer, Long id) {
        return beerRepository.findById(id)
                .map(existingBeer -> {
                    existingBeer.setName(beer.getName());
                    existingBeer.setDescription(beer.getDescription());
                    return beerRepository.save(existingBeer);
                })
                .orElseThrow(() -> new RuntimeException("Beer not found"));
    }
}
