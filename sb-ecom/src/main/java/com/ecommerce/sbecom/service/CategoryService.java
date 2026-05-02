package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.payload.CategoryDTO;
import com.ecommerce.sbecom.payload.CategoryResponse;

public interface CategoryService {

    CategoryResponse getAllCategories();

    void createCategory(CategoryDTO category);

    String deleteCategory(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, Category category);
}
