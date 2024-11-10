package org.example.cerveza.services;

import org.example.cerveza.entities.Style;
import org.example.cerveza.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StyleService {
    @Autowired
    private StyleRepository styleRepository;

    public List<Style> findAll() {
        return styleRepository.findAll();
    }

    public Optional<Style> findById(Long id) {
        return styleRepository.findById(id);
    }

    public Style save(Style style) {
        return styleRepository.save(style);
    }

    public void deleteById(Long id) {
        styleRepository.deleteById(id);
    }

    public Style updateStyle(Style style, Long id) {
        return styleRepository.findById(id)
                .map(existingStyle -> {
                    existingStyle.setName(style.getName());
                    existingStyle.setDescription(style.getDescription());
                    return styleRepository.save(existingStyle);
                })
                .orElseThrow(() -> new RuntimeException("Style not found"));
    }
}
