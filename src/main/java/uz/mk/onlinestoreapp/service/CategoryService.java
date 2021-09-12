package uz.mk.onlinestoreapp.service;

import uz.mk.onlinestoreapp.entity.Category;
import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.payload.ApiResponse;

import java.util.List;

public interface CategoryService {
    ApiResponse saveOrEditCategory(Category category);

    ApiResponse deleteCategory(Integer category_id);

    List<Category> getAllCategories();

    Category getCategoryById(Integer category_id);

    Category getCategoryByProductId(Integer product_id);
}
