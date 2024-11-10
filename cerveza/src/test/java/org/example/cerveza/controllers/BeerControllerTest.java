package org.example.cerveza.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cerveza.entities.Beer;
import org.example.cerveza.services.BeerService;
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
public class BeerControllerTest {

    @InjectMocks
    private BeerController beerController;

    @Mock
    private BeerService beerService;

    @Autowired
    private MockMvc mockMvc;

    private Beer beer;

    @BeforeEach
    public void setUp() {
        beer = new Beer(1L, "Beer Test", "A test beer", null, null, null);
    }

    @Test
    public void getAllBeers() throws Exception {
        when(beerService.findAll()).thenReturn(List.of(beer));

        mockMvc.perform(get("/beer/beers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Beer Test"));
    }

    @Test
    public void getBeerById() throws Exception {
        when(beerService.findById(1L)).thenReturn(Optional.of(beer));

        mockMvc.perform(get("/beer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Beer Test"));
    }

    @Test
    public void createBeer() throws Exception {
        when(beerService.save(any(Beer.class))).thenReturn(beer);

        mockMvc.perform(post("/beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(beer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Beer Test"));
    }

    @Test
    public void updateBeer() throws Exception {
        when(beerService.updateBeer(any(Beer.class), any(Long.class))).thenReturn(beer);

        mockMvc.perform(put("/beer/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(beer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Beer Test"));
    }

    @Test
    public void deleteBeer() throws Exception {
        mockMvc.perform(delete("/beer/1"))
                .andExpect(status().isNoContent());
    }
}
