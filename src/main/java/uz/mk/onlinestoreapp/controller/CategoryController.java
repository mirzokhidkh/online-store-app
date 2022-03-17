package uz.mk.onlinestoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mk.onlinestoreapp.entity.Category;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public HttpEntity<?> saveOrEditCategory(@Valid @RequestBody Category category) {
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

    @GetMapping("/details/{category_id}")
    public HttpEntity<?> getCategoryById(@PathVariable Integer category_id) {
        Category category = categoryService.getCategoryById(category_id);
        return ResponseEntity.ok(category);
    }

}
