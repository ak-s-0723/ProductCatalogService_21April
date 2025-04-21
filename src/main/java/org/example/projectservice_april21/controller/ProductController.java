package org.example.projectservice_april21.controller;

import org.example.projectservice_april21.client.FakeStoreClient;
import org.example.projectservice_april21.dto.FakeStoreProductDTO;
import org.example.projectservice_april21.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/product")
    public FakeStoreProductDTO createProduct(@RequestBody FakeStoreProductDTO fakeStoreProductDTO) {
        return productService.createProduct(fakeStoreProductDTO);
    }

    @PutMapping("/product/{id}")
    public FakeStoreProductDTO replaceProduct(@PathVariable("id") int id,
                                              @RequestBody FakeStoreProductDTO fakeStoreProductDTO) {
        return productService.replaceProduct(id,fakeStoreProductDTO);
    }

    @DeleteMapping("/product/{id}")
    public Boolean deleteProduct(@PathVariable("id") int id) {
        return productService.deleteProduct(id);
    }
}
