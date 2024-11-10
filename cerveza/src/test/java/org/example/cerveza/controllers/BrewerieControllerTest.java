package org.example.cerveza.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cerveza.entities.Brewerie;
import org.example.cerveza.services.BrewerieService;
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
public class BrewerieControllerTest {

    @InjectMocks
    private BrewerieController brewerieController;

    @Mock
    private BrewerieService brewerieService;

    @Autowired
    private MockMvc mockMvc;

    private Brewerie brewerie;

    @BeforeEach
    public void setUp() {
        brewerie = new Brewerie(1L, "Brewerie Test", "Location Test", "Description Test", null);
    }

    @Test
    public void getAllBreweries() throws Exception {
        when(brewerieService.findAll()).thenReturn(List.of(brewerie));

        mockMvc.perform(get("/brewerie/breweries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Brewerie Test"));
    }

    @Test
    public void getBrewerieById() throws Exception {
        when(brewerieService.findById(1L)).thenReturn(Optional.of(brewerie));

        mockMvc.perform(get("/brewerie/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Brewerie Test"));
    }

    @Test
    public void createBrewerie() throws Exception {
        when(brewerieService.save(any(Brewerie.class))).thenReturn(brewerie);

        mockMvc.perform(post("/brewerie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(brewerie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Brewerie Test"));
    }

    @Test
    public void updateBrewerie() throws Exception {
        when(brewerieService.updateBrewerie(any(Brewerie.class), any(Long.class))).thenReturn(brewerie);

        mockMvc.perform(put("/brewerie/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(brewerie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Brewerie Test"));
    }

    @Test
    public void deleteBrewerie() throws Exception {
        mockMvc.perform(delete("/brewerie/1"))
                .andExpect(status().isNoContent());
    }
}
