package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getCarBrand_whenPorsche() throws Exception {
        //WHEN
        mockMvc.perform(get("/api/cars/porsche"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().string("porsche"));

    }

    @Test
    void getCarBrand_whenVw() throws Exception {
        //WHEN
        mockMvc.perform(get("/api/cars/vw"))
                //THEN
                .andExpect(status().isForbidden())
                .andExpect(result -> assertInstanceOf(IllegalArgumentException.class, result.getResolvedException()));

    }

    @Test
    void getAllCars() throws Exception {
        //WHEN
        mockMvc.perform(get("/api/cars"))
                //THEN
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(NoSuchElementException.class, result.getResolvedException()));
    }
}