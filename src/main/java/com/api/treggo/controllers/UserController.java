package com.api.treggo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.treggo.entities.Users;
import com.api.treggo.repositories.UsersRepository;
import com.api.treggo.requests.ChangePasswordDTO;
import com.api.treggo.requests.CreateUser;
import com.api.treggo.requests.LoginDTO;
import com.api.treggo.responses.CreateUserResponse;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.responses.LoginResponse;
import com.api.treggo.services.PasswordService;
import com.api.treggo.services.UsersService;

import io.swagger.annotations.ApiOperation;

//Rest Controller
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	UsersService users;

	@Autowired
	PasswordService pwd;

	@Autowired
	UsersRepository repo;

	@ApiOperation(value = "Creates a new user of the system")
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody CreateUser request, @RequestHeader("x-tenant") String tenant) {
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		CreateUserResponse response = users.createNewUser(request, tenant);
		if (response.isValid()) {
			return ResponseEntity.ok().body(response);
		}

		else {
			return ResponseEntity.status(500).body(response);
		}
	}

	@ApiOperation(value = "Login using user credentials")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO request) {
		LoginResponse res = users.login(request);

		if (res.isStatus()) {
			return ResponseEntity.ok(res);
		}

		else {
			return ResponseEntity.status(500).body(res);
		}
	}

	@ApiOperation(value = "Get all the users")
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(@RequestHeader("x-tenant") String tenant) {
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		return ResponseEntity.ok(users.getAllUsers(tenant));
	}

	@ApiOperation(value = "Delete a particular user")
	@DeleteMapping("/deleteUser/{user_id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long user_id, @RequestHeader("x-tenant") String tenant) {
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		boolean response = users.deleteUser(user_id);

		if (response) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}

		else {
			return ResponseEntity.ok(new GeneralResponse("failure"));
		}
	}

	@ApiOperation(value = "Update user password")
	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody ChangePasswordDTO req,
			@RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}

		// Get existing user by username:
		Users user = users.getUserByUserName(req.getUsername(), tenant);
		if (user == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("Invalid Username"));
		}

		else {
			String dPassword = pwd.decrypt(user.getPassword());

			// Check if old password matches new password
			if (dPassword.equals(req.getOldPassword())) {

				// Update the existing user with new password:
				user.setPassword(pwd.encrypt(req.getNewPassword()));

				try {
					repo.save(user);
				}

				catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.status(500).body(new GeneralResponse("Network Failure"));
				}

				return ResponseEntity.ok(new GeneralResponse("success"));

			} else {
				return ResponseEntity.status(500).body(new GeneralResponse("Old Password didn't matched"));
			}
		}
	}

}
