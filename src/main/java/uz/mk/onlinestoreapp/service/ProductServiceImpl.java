package uz.mk.onlinestoreapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mk.onlinestoreapp.entity.Product;
import uz.mk.onlinestoreapp.exception.ResourceNotFoundException;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.ProductDTO;
import uz.mk.onlinestoreapp.repository.CategoryRepository;
import uz.mk.onlinestoreapp.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse saveOrEditProduct(ProductDTO productDTO) {
        boolean isIdNull = productDTO.getId() == null;
        if (isIdNull && productRepository.existsByNameAndCategoryId(productDTO.getName(),productDTO.getCategory_id())) {
            return new ApiResponse("Product with such a name '" + productDTO.getName() + "' and category ID '"+productDTO.getCategory_id()+"' already exists", false);
        } else if (!isIdNull && productRepository.existsByNameAndCategoryIdAndIdNot(productDTO.getName(),productDTO.getCategory_id(),productDTO.getId())) {
                return new ApiResponse("Product with such a name '" + productDTO.getName() + "' and category ID '"+productDTO.getCategory_id()+"' already exists", false);

        }
        Product product = createProduct(productDTO);
        if (isIdNull) {
            product.setId(productDTO.getId());
        }
        Product savedOrEditedProduct = productRepository.save(product);

        return new ApiResponse("Product " + (isIdNull ? "saved" : "edited"), true, savedOrEditedProduct);

    }

    @Override
    public ApiResponse deleteProduct(Integer product_id) {
        try {
            productRepository.deleteById(product_id);
            return new ApiResponse("Product with ID '" + product_id + " was deleted", true);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cannot Product with ID '" + product_id + "'. This product does not found");
        }
    }

    @Override
    public Product getProductById(Integer product_id) {
        return productRepository.findById(product_id).orElseThrow(() -> new ResourceNotFoundException("Product with ID '"+product_id+"' not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    private Product createProduct(ProductDTO productDTO) {
        return new Product(
                productDTO.getName(),
                categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(() -> new ResourceNotFoundException("Product with ID '"+productDTO.getCategory_id()+"' not found")),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getPhoto());
    }



}
