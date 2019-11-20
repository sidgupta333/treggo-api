package com.api.treggo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.treggo.entities.Dish;
import com.api.treggo.entities.DishCategory;
import com.api.treggo.requests.NewDishDTO;
import com.api.treggo.requests.UpdateCategoryDTO;
import com.api.treggo.requests.UpdateDIshStatusDTO;
import com.api.treggo.requests.UpdateImgDTO;
import com.api.treggo.requests.UpdatePriceDTO;
import com.api.treggo.responses.AllDishesResponse;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.services.DishesService;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin
@RequestMapping("/dishes")
public class DishesController {

	@Autowired
	private DishesService dishesService;

	
	
	@ApiOperation(value = "Creates a new Dish Category")
	@GetMapping("/createCategory/{category_name}/{category_id}")
	public ResponseEntity<?> createNewCategory(@PathVariable String category_name, @PathVariable Long category_id) {

		
		DishCategory output = dishesService.createCategory(category_name, category_id);
		if (output == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		} else {
			return ResponseEntity.ok(output);
		}
	}

	
	
	@ApiOperation(value = "Fetch all Dish Categories")
	@GetMapping("/allCategories")
	public List<DishCategory> getAllCategories() {

		return dishesService.getAllCategories();
	}

	
	
	@ApiOperation(value = "Fetch particular Dish Category by Dish Id")
	@GetMapping("/getDishCategory/{dishId}")
	public DishCategory getOneCategory(@PathVariable Long dishId) {

		return dishesService.getCategory(dishId);
	}

	
	
	@ApiOperation(value = "Delete a dish Category")
	@DeleteMapping("/deleteCategory/{category_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long category_id) {

		if (dishesService.deleteCategory(category_id)) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		} else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}

	
	
	@ApiOperation(value = "Update a dish Category")
	@PutMapping("/updateCategory")
	public ResponseEntity<?> updateCategory(@RequestBody UpdateCategoryDTO req) {

		if (req.getCategory_id() == null || req.getCategory_name() == null) {
			return ResponseEntity.status(400).body(new GeneralResponse("invalid"));
		}

		else {
			DishCategory response = dishesService.updateCategory(req.getCategory_id(), req.getCategory_name());

			if (response == null) {
				return ResponseEntity.status(500).body(new GeneralResponse("failure"));
			}

			return ResponseEntity.ok(response);
		}
	}

	
	
	@ApiOperation(value = "Create a new Dish")
	@PostMapping("/createDish")
	public ResponseEntity<?> createDish(@RequestBody NewDishDTO dto) {

		Dish d = dishesService.createDish(dto);
		if (d == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}

		else {
			return ResponseEntity.ok(d);
		}
	}

	
	
	@ApiOperation(value = "Fetch all available dishes")
	@GetMapping("/getAllDishes")
	public List<Dish> getAllDishes() {
		return dishesService.getAllDishes();
	}

	
	
	@ApiOperation(value = "Fetch Dish by Dish ID")
	@GetMapping("/getDish/ID/{dish_Id}")
	public Dish getDishById(@PathVariable Long dish_Id) {
		return dishesService.getDIshById(dish_Id);
	}

	
	
	@ApiOperation(value = "Fetch Dish by Dish Category ID")
	@GetMapping("/getDish/category/{category_Id}")
	public List<Dish> getDishByCategory(@PathVariable Long category_Id) {
		return dishesService.getDishesByCategory(category_Id);
	}

	
	
	@ApiOperation(value = "Delete a particular Dish")
	@DeleteMapping("/deleteDish/{dish_Id}")
	public ResponseEntity<?> deleteDish(@PathVariable Long dish_Id) {

		if (dishesService.deleteDish(dish_Id)) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		
		return ResponseEntity.status(500).body(new GeneralResponse("failure"));
	}

	
	
	@ApiOperation(value="Update price of existing Dish")
	@PostMapping("/updateDish/price")
	public ResponseEntity<?> updatePrice(@RequestBody UpdatePriceDTO dto) {
		
		Dish d = dishesService.updatePriceDish(dto.getDish_Id(), dto.getPrice());
		
		if(d == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		
		return ResponseEntity.ok(d);
	}
	
	
	@ApiOperation(value="Update status of existing Dish")
	@PostMapping("/updateDish/status")
	public ResponseEntity<?> updateStatus(@RequestBody UpdateDIshStatusDTO dto) {
		
		Dish d = dishesService.updateStatusDish(dto.getDish_Id(), dto.getStatus());
		if(d == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		
		return ResponseEntity.ok(d);
	}
	
	
	@ApiOperation(value="Update Image of existing Dish")
	@PostMapping("/updateDish/image")
	public ResponseEntity<?> updateImage(@RequestBody UpdateImgDTO dto) {
		
		Dish d = dishesService.updateImageDish(dto.getDish_Id(), dto.getImg());
		
		if(d == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		
		return ResponseEntity.ok(d);
	}
	
	@ApiOperation(value = "Get all the dishes in the hierarchy of dishes category")
	@GetMapping("/dishes/categoriesAll")
	public List<AllDishesResponse> allDishes() {
		
		return dishesService.allDishes();
	}

}
