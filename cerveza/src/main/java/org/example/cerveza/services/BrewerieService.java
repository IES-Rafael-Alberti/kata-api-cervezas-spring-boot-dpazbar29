package org.example.cerveza.services;

import org.example.cerveza.entities.Brewerie;
import org.example.cerveza.repositories.BrewerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrewerieService {
    @Autowired
    private BrewerieRepository brewerieRepository;

    public List<Brewerie> findAll() {
        return brewerieRepository.findAll();
    }

    public Optional<Brewerie> findById(Long id) {
        return brewerieRepository.findById(id);
    }

    public Brewerie save(Brewerie brewerie) {
        return brewerieRepository.save(brewerie);
    }

    public void deleteById(Long id) {
        brewerieRepository.deleteById(id);
    }

    public Brewerie updateBrewerie(Brewerie brewerie, Long id) {
        return brewerieRepository.findById(id)
                .map(existingBrewerie -> {
                    existingBrewerie.setName(brewerie.getName());
                    existingBrewerie.setLocatiion(brewerie.getLocatiion());
                    existingBrewerie.setDescription(brewerie.getDescription());
                    return brewerieRepository.save(existingBrewerie);
                })
                .orElseThrow(() -> new RuntimeException("Brewerie not found"));
    }
}
