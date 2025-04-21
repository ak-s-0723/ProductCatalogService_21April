package org.example.projectservice_april21.service;

import org.example.projectservice_april21.client.FakeStoreClient;
import org.example.projectservice_april21.dto.FakeStoreProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductDTO[] getAllProductsFromFakeStore(){
        return fakeStoreClient.getAllProducts();
    }

    public FakeStoreProductDTO getProductById(int productId){
        return fakeStoreClient.getProduct(productId);
    }

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO input) {
        return fakeStoreClient.createProduct(input);
    }

    public FakeStoreProductDTO replaceProduct(int id,FakeStoreProductDTO input) {
        return fakeStoreClient.replaceProduct(id,input);
    }

    public Boolean deleteProduct(int id) {
        return fakeStoreClient.deleteProduct(id);
    }
}
