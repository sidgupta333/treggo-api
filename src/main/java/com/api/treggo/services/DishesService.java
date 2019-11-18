package com.api.treggo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Dish;
import com.api.treggo.entities.DishCategory;
import com.api.treggo.repositories.DishCategoryRepository;
import com.api.treggo.repositories.DishRepository;
import com.api.treggo.requests.NewDishDTO;

@Service
public class DishesService {

	@Autowired
	private DishCategoryRepository categoryRepo;

	@Autowired
	private DishRepository dishRepo;

	// Save a new Dish category
	public DishCategory createCategory(String categoryName) {

		DishCategory dto = new DishCategory();
		dto.setCategory_name(categoryName);
		dto.setCreated_on(LocalDate.now());

		try {
			dto = categoryRepo.save(dto);
		}

		catch (Exception e) {
			return null;
		}

		if (dto == null) {
			return null;
		}

		return dto;
	}

	// Delete existing dish category
	public boolean deleteCategory(Long catId) {

		try {
			categoryRepo.deleteById(catId);
		}

		catch (Exception e) {
			return false;
		}

		return true;
	}

	// Fetch all existing dish categories
	public List<DishCategory> getAllCategories() {
		return categoryRepo.findAll();
	}

	// Fetch dish by Dish_id
	public DishCategory getCategory(Long id) {
		return categoryRepo.fetchByCategoryID(id);
	}

	// Update existing category
	public DishCategory updateCategory(Long id, String name) {

		DishCategory cg = new DishCategory();

		try {
			cg = categoryRepo.fetchByCategoryID(id);
			if (cg == null) {
				return null;
			} else {
				cg.setCategory_name(name);
				try {
					categoryRepo.save(cg);
				} catch (Exception e) {
					return null;
				}
			}
		} catch (Exception e) {
			return null;
		}

		return cg;
	}

	// Create a new Dish:
	public Dish createDish(NewDishDTO req) {

		Dish dto = new Dish();
		BeanUtils.copyProperties(req, dto);
		dto.setCreated_on(LocalDate.now());

		try {
			dto = dishRepo.save(dto);
		} catch (Exception e) {
			return null;
		}

		return dto;
	}

	// Fetch all dishes list
	public List<Dish> getAllDishes() {
		return dishRepo.findAll();
	}

	// Fetch dish by dish Id
	public Dish getDIshById(Long id) {
		return dishRepo.fetchByID(id);
	}

	// Fetch dish by category
	public List<Dish> getDishesByCategory(Long cat_id) {

		try {
			DishCategory cat = categoryRepo.fetchByCategoryID(cat_id);
			return dishRepo.fetchByCategory(cat);
		}

		catch (Exception e) {
			return null;
		}
	}

	// Delete a particular dish
	public boolean deleteDish(Long id) {

		try {
			dishRepo.deleteById(id);
		}

		catch (Exception e) {
			return false;
		}

		return true;
	}

	// Update base price of a dish
	public Dish updatePriceDish(Long dishId, Long price) {

		Dish dish = null;

		try {
			dish = dishRepo.fetchByID(dishId);
			dish.setBase_price(price);
			dishRepo.save(dish);
		}

		catch (Exception e) {
			return null;
		}

		return dish;
	}

	// Update dish image of existing dish
	public Dish updateImageDish(Long dishId, byte[] img) {
		Dish dish = null;

		try {
			dish = dishRepo.fetchByID(dishId);
			dish.setImg_data(img);
			dishRepo.save(dish);
		}

		catch (Exception e) {
			return null;
		}
		return dish;
	}

}
