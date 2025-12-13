package com.artisan.artisan;

import com.artisan.artisan.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetProducts() {
        ResponseEntity<Product[]> resp = restTemplate.getForEntity("/api/products", Product[].class);
        assertThat(resp.getStatusCodeValue()).isEqualTo(200);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().length).isGreaterThan(0);
    }

    @Test
    public void testCreateUpdateDeleteProduct() {
        Product p = new Product(null, "IntegrationTest", 10.0, "desc", "story", "Me", "Here", "Misc", true, "images/1.jpg");

        ResponseEntity<Product> created = restTemplate.postForEntity("/api/products", p, Product.class);
        assertThat(created.getStatusCodeValue()).isEqualTo(201);
        assertThat(created.getBody()).isNotNull();
        Long id = created.getBody().getId();

        // Update
        created.getBody().setName("UpdatedName");
        HttpEntity<Product> entity = new HttpEntity<>(created.getBody());
        ResponseEntity<Product> updated = restTemplate.exchange("/api/products/" + id, HttpMethod.PUT, entity, Product.class);
        assertThat(updated.getStatusCodeValue()).isEqualTo(200);
        assertThat(updated.getBody().getName()).isEqualTo("UpdatedName");

        // Delete
        restTemplate.delete("/api/products/" + id);
        ResponseEntity<Product> postDelete = restTemplate.getForEntity("/api/products/" + id, Product.class);
        assertThat(postDelete.getStatusCodeValue()).isEqualTo(404);
    }
}
