package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sellings")
public class SellingController {

    private final SellingService sellingService;

    @Autowired
    public SellingController(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @GetMapping
    public List<Selling> getAllSellings() {
        return sellingService.getAllSellings();
    }

    @GetMapping("/{id}")
    public Selling getSellingById(@PathVariable Long id) {
        return sellingService.getSellingById(id);
    }

    @PostMapping
    public ResponseEntity<String> createSelling(@RequestBody Selling selling) {
        Selling createdSelling = sellingService.createSelling(selling);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdSelling.getId()).toUri();
        return ResponseEntity.created(location).body("Продажа товара зарегистрирована успешно с ID " + createdSelling.getId() + ".");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSelling(@PathVariable Long id, @RequestBody Selling selling) {
        sellingService.updateSelling(id, selling);
        return ResponseEntity.ok("Продажа товара обновлена успешно.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSelling(@PathVariable Long id) {
        sellingService.deleteSelling(id);
        return ResponseEntity.ok("Продажа товара удалена успешно.");
    }
}
