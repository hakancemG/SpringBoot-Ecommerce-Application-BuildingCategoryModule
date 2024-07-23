package com.eticaret.springbootproject.controller;

import com.eticaret.springbootproject.model.Category;
import com.eticaret.springbootproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //@GetMapping("/api/public/categories")
    @RequestMapping(value="/public/categories", method =RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //@PostMapping("/api/admin/categories")
    @RequestMapping(value="/admin/categories", method =RequestMethod.POST)
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);
    }

    //@DeleteMapping("/api/admin/categories/{categoryId}")
    @RequestMapping(value="admin/categories/{categoryId}", method =RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try{
            String status = categoryService.deleteCategory(categoryId);
            //return new ResponseEntity<>(status, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
    //@PutMapping("/api/admin/categories/{categoryId}")
    @RequestMapping(value="admin/categories/{categoryId}", method =RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(@RequestBody Category category,
                                                 @PathVariable Long categoryId){
        try{
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Category saved!", HttpStatus.OK);
        }catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}