package com.api.treggo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Dish;
import com.api.treggo.entities.DishCategory;
import com.api.treggo.entities.ImgMaster;
import com.api.treggo.enums.YesNo;
import com.api.treggo.repositories.DishCategoryRepository;
import com.api.treggo.repositories.DishRepository;
import com.api.treggo.repositories.ImgMasterRepository;
import com.api.treggo.requests.NewDishDTO;
import com.api.treggo.responses.AllDishesResponse;

@Service
public class DishesService {

	@Autowired
	private DishCategoryRepository categoryRepo;

	@Autowired
	private DishRepository dishRepo;

	@Autowired
	private ImgMasterRepository imgRepo;

	// Save a new Dish category
	public DishCategory createCategory(String categoryName, Long category_id, String tenant) {

		DishCategory dto = new DishCategory();
		dto.setCategory_name(categoryName);
		
		if(category_id != 0) {
			dto.setCategory_id(category_id);
		}
		dto.setTenantCode(tenant);
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
	public boolean deleteCategory(Long catId, String tenant) {


		try {
			//Delete al dishes of this category
			List<Dish> dishes = dishRepo.fetchByCategory(catId, tenant);

			for (Dish dish:dishes) {
				this.deleteDish(dish.getDish_id(), tenant);
			}

			categoryRepo.deleteById(catId);
		}

		catch (Exception e) {
			return false;
		}

		return true;
	}

	// Fetch all existing dish categories
	public List<DishCategory> getAllCategories(String tenant) {
		return categoryRepo.findByTenantCode(tenant);
	}

	// Fetch dish by Dish_id
	public DishCategory getCategory(Long id, String tenant) {
		return categoryRepo.fetchByCategoryID(id, tenant);
	}

	// Update existing category
	public DishCategory updateCategory(Long id, String name, String tenant) {

		DishCategory cg = new DishCategory();

		try {
			cg = categoryRepo.fetchByCategoryID(id, tenant);
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
	public Dish createDish(NewDishDTO req, String tenant) {

		Dish dish = new Dish();
		
		
		BeanUtils.copyProperties(req, dish);
		
		DishCategory category = categoryRepo.fetchByCategoryID(req.getCategory_id(), tenant);
		dish.setCategory(category);
		
		if (req.getDish_id() == null) {
			ImgMaster img = imgRepo.fetchByImgID(req.getImg_id());
			dish.setImg(img);
		}
		
		else {

			Dish dish2 = dishRepo.fetchByID(req.getDish_id(), tenant);
			if (req.getImg_id() != null) {
				ImgMaster img = imgRepo.fetchByImgID(req.getImg_id());
				dish.setImg(img);
			} else {
				dish.setImg(dish2.getImg());
			}
		}

		dish.setTenantCode(tenant);
		dish.setCreated_on(LocalDate.now());

		try {
			dish = dishRepo.save(dish);
		} catch (Exception e) {
			return null;
		}

		return dish;
	}

	// Fetch all dishes list
	public List<Dish> getAllDishes(String tenant) {
		List<Dish> allDishes = dishRepo.findByTenantCode(tenant);
		List<Dish> res = new ArrayList<>();
		
		for(Dish dish: allDishes) {
			if(dish.getIs_available().equals(YesNo.Y)) {
				res.add(dish);
			}
		}
		
		return res;
	}

	// Fetch dish by dish Id
	public Dish getDIshById(Long id, String tenant) {
		return dishRepo.fetchByID(id, tenant);
	}

	// Fetch dish by category
	public List<Dish> getDishesByCategory(Long cat_id, String tenant) {

		try {
			DishCategory cat = categoryRepo.fetchByCategoryID(cat_id, tenant);
			return dishRepo.fetchByCategory(cat.getCategory_id(), tenant);
		}

		catch (Exception e) {
			return null;
		}
	}

	// Delete a particular dish
	public boolean deleteDish(Long id, String tenant) {

		try {
			Dish dish = dishRepo.fetchByID(id, tenant);
			ImgMaster img = dish.getImg();
			dishRepo.deleteById(id);

			// Remove image for the selected dish
			imgRepo.delete(img);
		}

		catch (Exception e) {
			return false;
		}

		return true;
	}

	// Update base price of a dish
	public Dish updatePriceDish(Long dishId, Long price, String tenant) {

		Dish dish = null;

		try {
			dish = dishRepo.fetchByID(dishId, tenant);
			dish.setBase_price(price);
			dishRepo.save(dish);
		}

		catch (Exception e) {
			return null;
		}

		return dish;
	}

	// Update dish availability status:
	public Dish updateStatusDish(Long dishId, String status, String tenant) {

		Dish dish = null;

		try {
			dish = dishRepo.fetchByID(dishId, tenant);
			if (status.equalsIgnoreCase("Y")) {
				dish.setIs_available(YesNo.Y);
			} else {
				dish.setIs_available(YesNo.N);
			}

			dishRepo.save(dish);
		}

		catch (Exception e) {
			return null;
		}

		return dish;
	}

	// Update dish image of existing dish
	public Dish updateImageDish(Long dishId, byte[] img, String tenant) {
		Dish dish = null;

		try {
			dish = dishRepo.fetchByID(dishId, tenant);
			dishRepo.save(dish);
		}

		catch (Exception e) {
			return null;
		}
		return dish;
	}

	// Get all dishes details with their dish category:
	public List<AllDishesResponse> allDishes(String tenant) {

		List<AllDishesResponse> output = new ArrayList<>();

		// Get all the dishes categories:
		List<DishCategory> categories = categoryRepo.findByTenantCode(tenant);

		for (DishCategory dishCategory : categories) {

			// Find dishes for each type of category:
			List<Dish> temp1 = this.getDishesByCategory(dishCategory.getCategory_id(), tenant);
			List<Dish> tempDishes = new ArrayList<>();
			
			for(Dish dish: temp1) {
				if(dish.getIs_available().equals(YesNo.Y)) {
					tempDishes.add(dish);
				}
			}
			
			AllDishesResponse temp = new AllDishesResponse();
			temp.setCategory_id(dishCategory.getCategory_id());
			temp.setCategory_name(dishCategory.getCategory_name());
			temp.setDishes(tempDishes);

			output.add(temp);
		}

		return output;

	}

}
