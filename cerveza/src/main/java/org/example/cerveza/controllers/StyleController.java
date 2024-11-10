package org.example.cerveza.controllers;

import org.example.cerveza.entities.Style;
import org.example.cerveza.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/style")
public class StyleController {

    @Autowired
    private StyleService styleService;

    @GetMapping("/styles")
    public List<Style> getAllStyles() {
        return styleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Style> getStyleById(@PathVariable Long id) {
        return styleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Style createStyle(@RequestBody Style style) {
        return styleService.save(style);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Style> updateStyle(@PathVariable Long id, @RequestBody Style style) {
        return ResponseEntity.ok(styleService.updateStyle(style, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStyle(@PathVariable Long id) {
        styleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
