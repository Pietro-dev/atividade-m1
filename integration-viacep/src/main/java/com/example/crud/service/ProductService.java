package com.example.crud.service;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SearchCityByCepService searchCityByCepService;

    public ProductService(ProductRepository productRepository, SearchCityByCepService searchCityByCepService) {
        this.productRepository = productRepository;
        this.searchCityByCepService =searchCityByCepService;
    }

    public String getDistributionCenterByProductId(String productId) {
        Product ProductReposiory = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("ID não corresponde a nenhum produto cadastrado!"));

        return ProductReposiory.getDistribution_center();
    }

    public Boolean checkProductCenter(String cep, String idProduct){
        String city = searchCityByCepService.SearchCityByCep(cep);
        String distributionCenter = getDistributionCenterByProductId(idProduct);

        if (city == null || distributionCenter == null) return false;

        return city.equalsIgnoreCase(distributionCenter);
    }
}
