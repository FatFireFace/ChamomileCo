package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SellingController.class)
@MockBean(SellingService.class)
@AutoConfigureMockMvc
public class SellingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllSellings() throws Exception {
        mockMvc.perform(get("/sellings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testGetSellingById() throws Exception {
        // Assuming there is a selling with ID 1
        mockMvc.perform(get("/sellings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testCreateSelling() throws Exception {
        Selling selling = new Selling();
        selling.setName("New Selling");
        selling.setAmount(50);
        selling.setProduct(new Product(1L, "Product Name", "Description", 100.0, true, 100));

        mockMvc.perform(post("/sellings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(selling)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/sellings/")));
    }

    @Test
    public void testUpdateSelling() throws Exception {
        Selling selling = new Selling();
        selling.setName("Updated Selling");
        selling.setAmount(100);
        selling.setProduct(new Product(1L, "Product Name", "Description", 100.0, true, 100));

        mockMvc.perform(put("/sellings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(selling)))
                .andExpect(status().isOk())
                .andExpect(content().string("Selling updated successfully"));
    }

    @Test
    public void testDeleteSelling() throws Exception {
        mockMvc.perform(delete("/sellings/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Selling deleted successfully"));
    }
}
