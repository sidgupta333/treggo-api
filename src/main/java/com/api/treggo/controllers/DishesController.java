package com.api.treggo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.treggo.entities.Dish;
import com.api.treggo.entities.DishCategory;
import com.api.treggo.requests.NewDishDTO;
import com.api.treggo.requests.UpdateCategoryDTO;
import com.api.treggo.requests.UpdateDIshStatusDTO;
import com.api.treggo.requests.UpdateImgDTO;
import com.api.treggo.requests.UpdatePriceDTO;
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
	public ResponseEntity<?> createNewCategory(@PathVariable String category_name, @PathVariable Long category_id,
			@RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		DishCategory output = dishesService.createCategory(category_name, category_id, tenant);
		if (output == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		} else {
			return ResponseEntity.ok(output);
		}
	}

	@ApiOperation(value = "Fetch all Dish Categories")
	@GetMapping("/allCategories")
	public ResponseEntity<?> getAllCategories(@RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		return ResponseEntity.ok(dishesService.getAllCategories(tenant));
	}

	@ApiOperation(value = "Fetch particular Dish Category by Dish Id")
	@GetMapping("/getDishCategory/{dishId}")
	public ResponseEntity<?> getOneCategory(@PathVariable Long dishId, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		return ResponseEntity.ok(dishesService.getCategory(dishId, tenant));
	}

	@ApiOperation(value = "Delete a dish Category")
	@DeleteMapping("/deleteCategory/{category_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long category_id, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		if (dishesService.deleteCategory(category_id, tenant)) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		} else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}

	@ApiOperation(value = "Update a dish Category")
	@PutMapping("/updateCategory")
	public ResponseEntity<?> updateCategory(@RequestBody UpdateCategoryDTO req,
			@RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		if (req.getCategory_id() == null || req.getCategory_name() == null) {
			return ResponseEntity.status(400).body(new GeneralResponse("invalid"));
		}

		else {
			DishCategory response = dishesService.updateCategory(req.getCategory_id(), req.getCategory_name(), tenant);

			if (response == null) {
				return ResponseEntity.status(500).body(new GeneralResponse("failure"));
			}

			return ResponseEntity.ok(response);
		}
	}

	@ApiOperation(value = "Create a new Dish")
	@PostMapping("/createDish")
	public ResponseEntity<?> createDish(@RequestBody NewDishDTO dto, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		Dish d = dishesService.createDish(dto, tenant);
		if (d == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}

		else {
			return ResponseEntity.ok(d);
		}
	}

	@ApiOperation(value = "Fetch all available dishes")
	@GetMapping("/getAllDishes")
	public ResponseEntity<?> getAllDishes(@RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		return ResponseEntity.ok(dishesService.getAllDishes(tenant));
	}

	@ApiOperation(value = "Fetch Dish by Dish ID")
	@GetMapping("/getDish/ID/{dish_Id}")
	public ResponseEntity<?> getDishById(@PathVariable Long dish_Id, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		return ResponseEntity.ok(dishesService.getDIshById(dish_Id, tenant));
	}

	@ApiOperation(value = "Fetch Dish by Dish Category ID")
	@GetMapping("/getDish/category/{category_Id}")
	public ResponseEntity<?> getDishByCategory(@PathVariable Long category_Id,
			@RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		return ResponseEntity.ok(dishesService.getDishesByCategory(category_Id, tenant));
	}

	@ApiOperation(value = "Delete a particular Dish")
	@DeleteMapping("/deleteDish/{dish_Id}")
	public ResponseEntity<?> deleteDish(@PathVariable Long dish_Id, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		if (dishesService.deleteDish(dish_Id, tenant)) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}

		return ResponseEntity.status(500).body(new GeneralResponse("failure"));
	}

	@ApiOperation(value = "Update price of existing Dish")
	@PostMapping("/updateDish/price")
	public ResponseEntity<?> updatePrice(@RequestBody UpdatePriceDTO dto, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		Dish d = dishesService.updatePriceDish(dto.getDish_Id(), dto.getPrice(), tenant);

		if (d == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}

		return ResponseEntity.ok(d);
	}

	@ApiOperation(value = "Update status of existing Dish")
	@PostMapping("/updateDish/status")
	public ResponseEntity<?> updateStatus(@RequestBody UpdateDIshStatusDTO dto,
			@RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		Dish d = dishesService.updateStatusDish(dto.getDish_Id(), dto.getStatus(), tenant);
		if (d == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}

		return ResponseEntity.ok(d);
	}

	@ApiOperation(value = "Update Image of existing Dish")
	@PostMapping("/updateDish/image")
	public ResponseEntity<?> updateImage(@RequestBody UpdateImgDTO dto, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		Dish d = dishesService.updateImageDish(dto.getDish_Id(), dto.getImg(), tenant);

		if (d == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}

		return ResponseEntity.ok(d);
	}

	@ApiOperation(value = "Get all the dishes in the hierarchy of dishes category")
	@GetMapping("/dishes/categoriesAll")
	public ResponseEntity<?> allDishes(@RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		return ResponseEntity.ok(dishesService.allDishes(tenant));
	}

}
