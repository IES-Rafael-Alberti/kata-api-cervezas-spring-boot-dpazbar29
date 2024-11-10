package org.example.cerveza.services;

import org.example.cerveza.entities.Categorie;
import org.example.cerveza.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> findById(Long id) {
        return categorieRepository.findById(id);
    }

    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteById(Long id) {
        categorieRepository.deleteById(id);
    }

    public Categorie updateCategorie(Categorie categorie, Long id) {
        return categorieRepository.findById(id)
                .map(existingCategorie -> {
                    existingCategorie.setName(categorie.getName());
                    existingCategorie.setDescription(categorie.getDescription());
                    return categorieRepository.save(existingCategorie);
                })
                .orElseThrow(() -> new RuntimeException("Categorie not found"));
    }
}
