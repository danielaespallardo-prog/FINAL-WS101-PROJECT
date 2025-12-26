package com.artisan.artisan;

import com.artisan.artisan.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetProducts() {
        ResponseEntity<Product[]> response = restTemplate.getForEntity("/api/products", Product[].class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        // If you want to test with data, ensure you have test data in your test database
        // Otherwise, you can just check that the endpoint returns successfully
    }

    @Test
    public void testCreateUpdateDeleteProduct() {
        // Create product using your constructor
        Product product = new Product();
        product.setName("Integration Test Product");
        product.setDescription("Test description for integration test");
        product.setPrice(149.99);
        product.setCategory("Test Category");
        
        // Create
        ResponseEntity<Product> createResponse = restTemplate.postForEntity("/api/products", product, Product.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();
        
        Long productId = createResponse.getBody().getId();
        
        // Update
        Product updatedProduct = createResponse.getBody();
        updatedProduct.setName("Updated Integration Test");
        updatedProduct.setPrice(199.99);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> updateEntity = new HttpEntity<>(updatedProduct, headers);
        
        ResponseEntity<Product> updateResponse = restTemplate.exchange(
            "/api/products/" + productId, 
            HttpMethod.PUT, 
            updateEntity, 
            Product.class
        );
        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updateResponse.getBody().getName()).isEqualTo("Updated Integration Test");
        
        // Delete
        restTemplate.delete("/api/products/" + productId);
        
        // Verify deletion
        ResponseEntity<Product> getResponse = restTemplate.getForEntity("/api/products/" + productId, Product.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}