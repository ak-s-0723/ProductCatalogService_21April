package org.example.projectservice_april21.controller;

import org.example.projectservice_april21.dto.FakeStoreProductDTO;
import org.example.projectservice_april21.service.ProductService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public FakeStoreProductDTO[] getAllProducts() {
        return productService.getAllProductsFromFakeStore();
    }

    @GetMapping("/product/{id}")
    public FakeStoreProductDTO getProduct(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }
}
