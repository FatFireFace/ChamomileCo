package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SellingService {

    private final SellingRepository sellingRepository;

    @Autowired
    public SellingService(SellingRepository sellingRepository) {
        this.sellingRepository = sellingRepository;
    }

    public List<Selling> getAllSellings() {
        return sellingRepository.findAll();
    }

    public Selling getSellingById(Long id) {
        return sellingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Selling", "id", id));
    }

    public Selling createSelling(Selling selling) {
        return sellingRepository.save(selling);
    }

    public Selling updateSelling(Long id, Selling updatedSelling) {
        Selling existingSelling = getSellingById(id);
        existingSelling.setName(updatedSelling.getName());
        existingSelling.setAmount(updatedSelling.getAmount());
        existingSelling.setProduct(updatedSelling.getProduct());
        return sellingRepository.save(existingSelling);
    }

    public void deleteSelling(Long id) {
        sellingRepository.deleteById(id);
    }
}