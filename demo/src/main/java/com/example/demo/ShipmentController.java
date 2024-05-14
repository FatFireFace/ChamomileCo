package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    public Shipment getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id);
    }

    @PostMapping
    public ResponseEntity<String> createShipment(@RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentService.createShipment(shipment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdShipment.getId()).toUri();
        return ResponseEntity.created(location).body("Поставка товара зарегистрирована успешно с ID " + createdShipment.getId() + ".");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateShipment(@PathVariable Long id, @RequestBody Shipment shipment) {
        shipmentService.updateShipment(id, shipment);
        return ResponseEntity.ok("Поставка товара обновлена.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.ok("Поставка товара удалена.");
    }
}