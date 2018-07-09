package com.arun.spring.springrest.springrestfirst.api.user;

import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

	@Autowired
	private  UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(path = "/jpa/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{userid}")
	public Resource<User> findUser(@PathVariable int userid) {
		Optional<User> user = userRepository.findById(userid);
		if (!user.isPresent()) {
			
				throw new UserNotFoundException("id =" + userid);
			
		}
		
		Resource<User> resource = new Resource<User>(user.get());
		 ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
		resource.add(linkTo.withRel("all-users"));
		 return resource;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		// to get current request uri
		UriComponents location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId());

		return ResponseEntity.created(location.toUri()).build();
	}
	
	@DeleteMapping(path = "/jpa/users/{userid}")
	public void deleteUser(@PathVariable int userid) {
		System.out.println("inside delete");
		userRepository.deleteById(userid);	
	}

	
	@GetMapping(path = "/jpa/users/{userid}/posts")
	public List<Post> retrievealluserPosts(@PathVariable int userid) {
		Optional<User> user = userRepository.findById(userid);
		if (!user.isPresent()) {
			
				throw new UserNotFoundException("id =" + userid);
			
		}		
		
		 return user.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/users/{userid}/posts")
	public ResponseEntity<Post> savePosts(@PathVariable int userid ,@RequestBody Post post ) {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent()) {
			
				throw new UserNotFoundException("id =" + userid);
			
		}	
		User user = userOptional.get();
		post.setUser(user);		
		  postRepository.save(post);
		  
		  UriComponents location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(post.getId());

			return ResponseEntity.created(location.toUri()).build();
	}
	
	@GetMapping(path = "/jpa/users/{userid}/posts/{postid}")
	public Post retrievealluserPosts(@PathVariable("userid") int userid,@PathVariable("postid") int postid) {
		Optional<User> user = userRepository.findById(userid);
		if (!user.isPresent()) {
			
				throw new UserNotFoundException("id =" + userid);
			
		}		
		Post post = null;
		 List<Post> postsOfUser = user.get().getPosts();
		 for(Post posts:postsOfUser){
			 if(posts.getId().equals(postid)){
				 post=posts;
			 }
		 }
		 return post;
	}
}
