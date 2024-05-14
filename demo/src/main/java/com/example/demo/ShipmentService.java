package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment", "id", id));
    }

    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipment(Long id, Shipment updatedShipment) {
        Shipment existingShipment = getShipmentById(id);
        existingShipment.setName(updatedShipment.getName());
        existingShipment.setAmount(updatedShipment.getAmount());
        existingShipment.setProduct(updatedShipment.getProduct());
        return shipmentRepository.save(existingShipment);
    }

    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }
}
