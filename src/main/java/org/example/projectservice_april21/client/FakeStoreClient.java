package org.example.projectservice_april21.client;

import org.example.projectservice_april21.dto.FakeStoreProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {

    @Autowired
    private RestTemplate restTemplate;
    // error - Could not autowire. No beans of 'RestTemplate' type found.

    public FakeStoreProductDTO[] getAllProducts(){
        String getAllProductURL = "https://fakestoreapi.com/products";
        FakeStoreProductDTO[] response = restTemplate.getForObject(getAllProductURL, FakeStoreProductDTO[].class);
        return response;
    }

//    public FakeStoreProductDTO getProduct(int productId){
//        String getProduct = "https://fakestoreapi.com/products/" + productId;
//        FakeStoreProductDTO response = restTemplate.getForObject(getProduct, FakeStoreProductDTO.class);
//        return response;
//    }

    public FakeStoreProductDTO getProduct(int productId){
        String getProduct = "https://fakestoreapi.com/products/" + productId;
        FakeStoreProductDTO response =
                requestForObject(getProduct,HttpMethod.GET,null, FakeStoreProductDTO.class);
        return response;
    }

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO input) {
        String createProduct = "https://fakestoreapi.com/products";
        FakeStoreProductDTO response = restTemplate.postForObject(createProduct,
                input, FakeStoreProductDTO.class);
        return response;
    }

    public FakeStoreProductDTO replaceProduct(int productId,FakeStoreProductDTO input) {
        String replaceProduct = "https://fakestoreapi.com/products/" + productId;
        FakeStoreProductDTO response = requestForObject(replaceProduct,HttpMethod.PUT,input, FakeStoreProductDTO.class);
        return response;
    }

    public Boolean deleteProduct(int productId) {
        String deleteProduct = "https://fakestoreapi.com/products/" + productId;
        try {
            requestForObject(deleteProduct,HttpMethod.DELETE,null, FakeStoreProductDTO.class);
            return true;
        }catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }


//    private <T> T putForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
//        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
//        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, restTemplate.getMessageConverters());
//        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, (Object[])uriVariables);
//    }

    private <T> T requestForObject(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, restTemplate.getMessageConverters());
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, (Object[])uriVariables);
    }
}
