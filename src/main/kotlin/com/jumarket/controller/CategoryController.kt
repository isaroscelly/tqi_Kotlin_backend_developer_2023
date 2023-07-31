package com.jumarket.controller

import com.jumarket.domain.Category
import com.jumarket.service.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(private val categoryService: CategoryService) {

    @PostMapping
    fun createCategory(@RequestBody category: Category): Category {
        return categoryService.create(category)
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): Category {
        return categoryService.findById(id).orElseThrow { NoSuchElementException("Categoria com ID $id não encontrada.") }
    }

    @GetMapping
    fun getAllCategories(): List<Category> {
        return categoryService.findAll()
    }


    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long) {
        categoryService.deleteById(id)
    }
}