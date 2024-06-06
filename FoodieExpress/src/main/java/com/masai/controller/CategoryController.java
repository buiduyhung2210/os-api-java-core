package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CategoryException;
import com.masai.model.Category;
import com.masai.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	public CategoryService cService;

	@PostMapping("/addCategory")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Category> addCategory(@RequestBody Category cat) throws CategoryException {
		System.out.println(cat);

		Category cat2 = cService.addCategory(cat);

		return new ResponseEntity<Category>(cat2, HttpStatus.CREATED);
	}

	@PutMapping("/updateCategory")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Category> updateCategory(@RequestBody Category cat) throws CategoryException {

		Category cat2 = cService.updateCategory(cat);

		return new ResponseEntity<Category>(cat2, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteCategory/{categoryId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Category> removeCategory(@PathVariable Integer categoryId) throws CategoryException {

		Category cat2 = cService.removeCategory(categoryId);

		return new ResponseEntity<Category>(cat2, HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewCategory/{categoryId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Category> viewCategory(@PathVariable Integer categoryId) throws CategoryException {

		Category cat2 = cService.viewCategory(categoryId);

		return new ResponseEntity<Category>(cat2, HttpStatus.OK);
	}

	@GetMapping("/allCategory")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<Category>> viewAllCategory() throws CategoryException {

		List<Category> categoryList = cService.viewAllCategory();

		return new ResponseEntity<>(categoryList, HttpStatus.ACCEPTED);
	}

}
