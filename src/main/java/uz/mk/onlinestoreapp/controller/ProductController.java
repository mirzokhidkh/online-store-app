package uz.mk.onlinestoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.mk.onlinestoreapp.entity.Product;
import uz.mk.onlinestoreapp.exception.ResourceNotFoundException;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.payload.ProductDTO;
import uz.mk.onlinestoreapp.service.ProductService;
import uz.mk.onlinestoreapp.service.MapValidationErrorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public ProductController(ProductService ProductService, MapValidationErrorService mapValidationErrorService) {
        this.productService = ProductService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping
    public HttpEntity<?> saveOrEditProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
        HttpEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) {
            return errorMap;
        }
        ApiResponse apiResponse = productService.saveOrEditProduct(productDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/{product_id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer product_id) {
        ApiResponse apiResponse = productService.deleteProduct(product_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/list")
    public Iterable<Product>  getAllProducts(){
        return productService.getAllProducts();
    }


    @GetMapping("/details")
    public HttpEntity<?> getProductById(@RequestParam Integer product_id) {
        Product product = productService.getProductById(product_id);
        return ResponseEntity.ok(product);
    }

}
