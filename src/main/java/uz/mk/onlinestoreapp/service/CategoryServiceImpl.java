package uz.mk.onlinestoreapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mk.onlinestoreapp.entity.Category;
import uz.mk.onlinestoreapp.entity.Customer;
import uz.mk.onlinestoreapp.exception.ResourceNotFoundException;
import uz.mk.onlinestoreapp.payload.ApiResponse;
import uz.mk.onlinestoreapp.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse saveOrEditCategory(Category category) {
        boolean isIdNull = category.getId() == null;
        if (isIdNull && categoryRepository.existsByName(category.getName())) {
            return new ApiResponse("Category with such a name '" + category.getName() + "' already exists", false);
        } else if (!isIdNull && categoryRepository.existsByNameAndIdNot(category.getName(), category.getId())) {
            return new ApiResponse("Category with such a name '" + category.getName() + "' already exists", false);
        }
        Category savedOrEditedCategory = categoryRepository.save(category);
        return new ApiResponse("Category " + (isIdNull ? "saved" : "edited"), true, savedOrEditedCategory);
    }


    @Override
    public ApiResponse deleteCategory(Integer category_id) {
        try {
            categoryRepository.deleteById(category_id);
            return new ApiResponse("Category with ID '" + category_id + " was deleted", true);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cannot Category with ID '" + category_id + "'. This category does not found");
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer category_id) {
        return categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category with ID '" + category_id + "' not found"));
    }

    @Override
    public Category getCategoryByProductId(Integer product_id) {
        return categoryRepository.findByProductId(product_id);
    }
}
