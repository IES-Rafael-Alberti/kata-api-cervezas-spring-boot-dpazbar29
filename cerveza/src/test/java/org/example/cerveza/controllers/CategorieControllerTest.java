package org.example.cerveza.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cerveza.entities.Categorie;
import org.example.cerveza.services.CategorieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CategorieControllerTest {

    @InjectMocks
    private CategorieController categorieController;

    @Mock
    private CategorieService categorieService;

    @Autowired
    private MockMvc mockMvc;

    private Categorie categorie;

    @BeforeEach
    public void setUp() {
        categorie = new Categorie(1L, "Categorie Test", "Description Test", null);
    }

    @Test
    public void getAllCategories() throws Exception {
        when(categorieService.findAll()).thenReturn(List.of(categorie));

        mockMvc.perform(get("/categorie/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Categorie Test"));
    }

    @Test
    public void getCategorieById() throws Exception {
        when(categorieService.findById(1L)).thenReturn(Optional.of(categorie));

        mockMvc.perform(get("/categorie/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Categorie Test"));
    }

    @Test
    public void createCategorie() throws Exception {
        when(categorieService.save(any(Categorie.class))).thenReturn(categorie);

        mockMvc.perform(post("/categorie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categorie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Categorie Test"));
    }

    @Test
    public void updateCategorie() throws Exception {
        when(categorieService.updateCategorie(any(Categorie.class), any(Long.class))).thenReturn(categorie);

        mockMvc.perform(put("/categorie/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categorie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Categorie Test"));
    }

    @Test
    public void deleteCategorie() throws Exception {
        mockMvc.perform(delete("/categorie/1"))
                .andExpect(status().isNoContent());
    }
}
