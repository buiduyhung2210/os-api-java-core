package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CategoryException;
import com.masai.model.Category;
import com.masai.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	public CategoryRepo cRepo;

	@Override
	public Category addCategory(Category cat) throws CategoryException {
		System.out.println(cat);

		if (cat == null) {
			throw new CategoryException("Category not be null");
		}
		return cRepo.save(cat);
	}

	@Override
	public Category updateCategory(Category cat) throws CategoryException {

		cRepo.findById(cat.getCategoryId()).orElseThrow(() -> new CategoryException("Category not available for updation"));

		return cRepo.save(cat);

	}

	@Override
	public Category viewCategory(Integer categoryId) throws CategoryException {

		Category category = cRepo.findById(categoryId)
				.orElseThrow(() -> new CategoryException("Category not available for view"));

		return category;
	}

	@Override
	public List<Category> viewAllCategory() throws CategoryException {

		List<Category> categories = cRepo.findAll();

		if (categories.size() == 0) {
			throw new CategoryException("Categories is empty");
		}
		return categories;
	}

	@Override
	public Category removeCategory(Integer categoryId) throws CategoryException {
		Category category = cRepo.findById(categoryId)
				.orElseThrow(() -> new CategoryException("Category not available for deletation"));

		cRepo.deleteById(categoryId);

		return category;
	}

}
