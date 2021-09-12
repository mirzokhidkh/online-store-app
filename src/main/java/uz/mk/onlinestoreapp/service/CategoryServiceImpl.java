package uz.mk.onlinestoreapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mk.onlinestoreapp.entity.Category;
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
        if (category.getId() == null) {
            if (categoryRepository.existsByName(category.getName())) {
                return new ApiResponse("Category with such a name already exists", false);
            }
            Category savedCategory = categoryRepository.save(category);
            return new ApiResponse("Category saved", true, savedCategory);
        } else {
            if (categoryRepository.existsByNameAndIdNot(category.getName(), category.getId())) {
                return new ApiResponse("Category with such a name '"+category.getName()+"' already exists", false);
            }
            Category editedCategory = categoryRepository.save(category);
            return new ApiResponse("Category ID '" + category.getId() + "' edited", true, editedCategory);
        }
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
    public Category getCategoryByProductId(Integer product_id) {
        return categoryRepository.findByProductId(product_id);
    }
}
