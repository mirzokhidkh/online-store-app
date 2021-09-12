package uz.mk.onlinestoreapp.service;

import uz.mk.onlinestoreapp.entity.Product;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.ProductDTO;

import java.util.List;

public interface ProductService {
    ApiResponse saveOrEditProduct(ProductDTO productDTO);

    ApiResponse deleteProduct(Integer product_id);

    Product getProductById(Integer product_id);

    List<Product> getAllProducts();

}
