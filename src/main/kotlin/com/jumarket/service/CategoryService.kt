package com.jumarket.service

import com.jumarket.domain.Category
import com.jumarket.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun create(category: Category): Category {
        return categoryRepository.save(category)
    }

    fun findById(id: Long): Optional<Category> {
        return categoryRepository.findById(id)
    }

    fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }


    fun deleteById(id: Long) {
        categoryRepository.deleteById(id)
    }
}