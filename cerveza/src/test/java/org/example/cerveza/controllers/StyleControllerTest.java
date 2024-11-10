package org.example.cerveza.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cerveza.entities.Style;
import org.example.cerveza.services.StyleService;
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
public class StyleControllerTest {

    @InjectMocks
    private StyleController styleController;

    @Mock
    private StyleService styleService;

    @Autowired
    private MockMvc mockMvc;

    private Style style;

    @BeforeEach
    public void setUp() {
        style = new Style(1L, "Style Test", "Description Test", null);
    }

    @Test
    public void getAllStyles() throws Exception {
        when(styleService.findAll()).thenReturn(List.of(style));

        mockMvc.perform(get("/style/styles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Style Test"));
    }

    @Test
    public void getStyleById() throws Exception {
        when(styleService.findById(1L)).thenReturn(Optional.of(style));

        mockMvc.perform(get("/style/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Style Test"));
    }

    @Test
    public void createStyle() throws Exception {
        when(styleService.save(any(Style.class))).thenReturn(style);

        mockMvc.perform(post("/style")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(style)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Style Test"));
    }

    @Test
    public void updateStyle() throws Exception {
        when(styleService.updateStyle(any(Style.class), any(Long.class))).thenReturn(style);

        mockMvc.perform(put("/style/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(style)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Style Test"));
    }

    @Test
    public void deleteStyle() throws Exception {
        mockMvc.perform(delete("/style/1"))
                .andExpect(status().isNoContent());
    }
}
