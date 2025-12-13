package com.artisan.artisan.service;

import com.artisan.artisan.model.Product;
import com.artisan.artisan.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getById(long id) {
        return repository.findById(id);
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Optional<Product> updateProduct(long id, Product updated) {
        return repository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            existing.setDescription(updated.getDescription());
            existing.setStory(updated.getStory());
            existing.setArtisan(updated.getArtisan());
            existing.setLocation(updated.getLocation());
            existing.setCategory(updated.getCategory());
            existing.setInStock(updated.isInStock());
            existing.setImage(updated.getImage());
            return repository.save(existing);
        });
    }

    public boolean deleteProduct(long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
