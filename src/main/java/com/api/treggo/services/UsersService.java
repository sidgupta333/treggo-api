package com.api.treggo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Users;
import com.api.treggo.enums.YesNo;
import com.api.treggo.repositories.UsersRepository;
import com.api.treggo.requests.CreateUser;
import com.api.treggo.requests.LoginDTO;
import com.api.treggo.responses.CreateUserResponse;
import com.api.treggo.responses.LoginResponse;

@Service
public class UsersService {

	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private PasswordService pwd;

	// Service to create a new user in the system
	public CreateUserResponse createNewUser(CreateUser req) {
		
		CreateUserResponse response = new CreateUserResponse();
		
		// Validate request
		System.out.println("Admin --> " + req.getIs_admin());
		if (req.getFull_name() == null) {
			response.setValid(false);
			response.setMessage("Full Name is required");
		}
		
		else if (req.getUsername() == null) {
			response.setValid(false);
			response.setMessage("Username is required");
		}
		
		else if (req.getPassword() == null) {
			response.setValid(false);
			response.setMessage("Password is required");
		}
		
		else {
			
			Users userToSave = new Users();
			userToSave.setFull_name(req.getFull_name());
			
			if(req.getIs_admin().equals("Y")) {
				userToSave.setIs_admin(YesNo.Y);	
			}
			else {
				userToSave.setIs_admin(YesNo.N);	
			}
			userToSave.setPassword(pwd.encrypt(req.getPassword()));
			userToSave.setUsername(req.getUsername());
			userToSave.setCreated_on(LocalDate.now());
			
			if(req.getUser_id() != null) {
				userToSave.setUser_id(req.getUser_id());
			}
			
			try {

				// Check if existing user exists:
				Users exists = userRepo.findByUsername(req.getUsername());
				if(exists != null && req.getUser_id() == null) {
					response.setValid(false);
					response.setMessage("Exists");
				}
				else {
					userRepo.save(userToSave);			
					response.setValid(true);
					response.setMessage("Success");
					response.setFull_name(req.getFull_name());
					response.setUsername(req.getUsername());
					
				}

			}
			
			catch(Exception e) {
				e.printStackTrace();
				response.setValid(false);
				response.setMessage("Failed to save");
				return response;
			}		
		}
		
		return response;
	}
	
	
	// Get all the users list
	public List<Users> getAllUsers() {
		return userRepo.findAll();
	}
	
	
	// Delete a particular user
	public boolean deleteUser(Long user_id) {
		
		boolean status = true;
		try {
			userRepo.deleteById(user_id);
		}
		catch(Exception e) {
			status = false;
			e.printStackTrace();
			return status;
		}
		
		return status;
	}
	
	
	// Get selected user by Id
	public Users getUserById(Long user_id) {
		return userRepo.getOne(user_id);
	}
	
	
	//Get selected user by Username
	public Users getUserByUserName(String username) {
		return userRepo.findByUsername(username);
	}
	
	//Validate user login
	public LoginResponse login(LoginDTO req) {
		
		LoginResponse response = new LoginResponse();
		Users user;
		try {
			user = userRepo.findByUsername(req.getUsername());			
		}
		
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Network failure");
			return response;
		}
		
		if(user == null) {
			response.setStatus(false);
			response.setMessage("failure");
		}
		
		else {
			String dPassword = pwd.decrypt(user.getPassword()); 
			if(user.getUsername().equals(req.getUsername())  && dPassword.equals(req.getPassword())) {
				response.setStatus(true);
				response.setMessage("success");
				response.setFullName(user.getFull_name());
				response.setUsername(user.getUsername());
				response.setIs_admin(user.getIs_admin());
			}
			else {
				response.setStatus(false);
				response.setMessage("failure");
			}
		}
		
		return response;
		
	}
	
}
