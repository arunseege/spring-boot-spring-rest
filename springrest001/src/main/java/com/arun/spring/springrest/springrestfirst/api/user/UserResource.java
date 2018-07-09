package com.arun.spring.springrest.springrestfirst.api.user;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import javax.validation.Valid;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.assertj.core.api.UriAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.arun.spring.springrest.springrestfirst.helpers.UserNotFoundException;
import com.arun.user.dao.UserDAOService;

@RestController
public class UserResource {

	@Autowired
	private UserDAOService daoService;

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return daoService.findAll();
	}

	@GetMapping(path = "/users/{userid}")
	public Resource<User> findUser(@PathVariable int userid) {
		User user = daoService.findOne(userid);
		if (user == null) {
			
				throw new UserNotFoundException("id =" + userid);
			
		}
		
		Resource<User> resource = new Resource<User>(user);
		 ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
		resource.add(linkTo.withRel("all-users"));
		 return resource;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User savedUser = daoService.saveUser(user);
		// to get current request uri
		UriComponents location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId());

		return ResponseEntity.created(location.toUri()).build();
	}
	
	@DeleteMapping(path = "/users/{userid}")
	public void deleteUser(@PathVariable int userid) {
		System.out.println("inside delete");
		User user = daoService.deleteById(userid);
		if (user == null) {			
				throw new UserNotFoundException("id =" + userid);			
		}
		
	}

}
