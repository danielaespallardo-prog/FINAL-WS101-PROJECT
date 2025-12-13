package com.artisan.artisan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;
    @PositiveOrZero(message = "Price must be non-negative")
    private double price;
    @NotBlank(message = "Description is required")
    private String description;
    private String story;
    private String artisan;
    private String location;
    private String category;
    private boolean inStock;
    private String image;

    public Product() {}

    public Product(Long id, String name, double price, String description, String story, String artisan, String location, String category, boolean inStock, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.story = story;
        this.artisan = artisan;
        this.location = location;
        this.category = category;
        this.inStock = inStock;
        this.image = image;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStory() { return story; }
    public void setStory(String story) { this.story = story; }
    public String getArtisan() { return artisan; }
    public void setArtisan(String artisan) { this.artisan = artisan; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
