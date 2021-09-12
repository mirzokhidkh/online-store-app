package uz.mk.onlinestoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.mk.onlinestoreapp.entity.Category;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.service.CategoryService;
import uz.mk.onlinestoreapp.service.MapValidationErrorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public CategoryController(CategoryService categoryService, MapValidationErrorService mapValidationErrorService) {
        this.categoryService = categoryService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping
    public HttpEntity<?> saveOrEditCategory(@Valid @RequestBody Category category, BindingResult result) {
        HttpEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) {
            return errorMap;
        }
        ApiResponse apiResponse = categoryService.saveOrEditCategory(category);
        return ResponseEntity.status(apiResponse.isStatus() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/{category_id}")
    public HttpEntity<?> deleteCategory(@PathVariable Integer category_id) {
        ApiResponse apiResponse = categoryService.deleteCategory(category_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/list")
    public Iterable<Category>  getAllCategories(){
        return categoryService.getAllCategories();
    }


    @GetMapping
    public HttpEntity<?> getCategoryByProductId(@RequestParam Integer product_id) {
        Category category = categoryService.getCategoryByProductId(product_id);
        return ResponseEntity.ok(category);
    }

}
