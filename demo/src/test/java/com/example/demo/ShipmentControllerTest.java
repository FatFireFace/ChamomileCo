package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShipmentController.class)
@MockBean(ShipmentService.class)
@AutoConfigureMockMvc
public class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllShipments() throws Exception {
        mockMvc.perform(get("/shipments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testGetShipmentById() throws Exception {
        // Assuming there is a shipment with ID 1
        mockMvc.perform(get("/shipments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testCreateShipment() throws Exception {
        Shipment shipment = new Shipment();
        shipment.setName("New Shipment");
        shipment.setAmount(100);
        shipment.setProduct(new Product(1L, "Product Name", "Description", 100.0, true, 100));

        mockMvc.perform(post("/shipments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shipment)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/shipments/")));
    }

    @Test
    public void testUpdateShipment() throws Exception {
        Shipment shipment = new Shipment();
        shipment.setName("Updated Shipment");
        shipment.setAmount(200);
        shipment.setProduct(new Product(1L, "Product Name", "Description", 100.0, true, 100));

        mockMvc.perform(put("/shipments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shipment)))
                .andExpect(status().isOk())
                .andExpect(content().string("Shipment updated successfully"));
    }

    @Test
    public void testDeleteShipment() throws Exception {
        mockMvc.perform(delete("/shipments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Shipment deleted successfully"));
    }
}