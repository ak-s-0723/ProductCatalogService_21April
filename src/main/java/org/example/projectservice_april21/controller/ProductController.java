package org.example.projectservice_april21.controller;

import org.example.projectservice_april21.client.FakeStoreClient;
import org.example.projectservice_april21.dto.FakeStoreProductDTO;
import org.example.projectservice_april21.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<FakeStoreProductDTO> getProduct(@PathVariable("id") int id) {
        if(id <= 0 ) {
            throw new IllegalArgumentException("Product doesn't exist");
            //return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        FakeStoreProductDTO fakeStoreProductDTO = productService.getProductById(id);
        return new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleAllExceptions(Exception exception) {
//        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
//    }


//    @ExceptionHandler(NullPointerException.class)
//    public String handleNullPointerExceptions(Exception exception) {
//        return exception.getMessage();
//    }


}
