package com.arun.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.arun.spring.springrest.springrestfirst.api.user.User;

@Component
public class UserDAOService {

	static List<User> users = new ArrayList<>();
	static int userCount = 3;

	static {
		users.add(new User("adam", new Date(), 1));
		users.add(new User("eve", new Date(), 2));
		users.add(new User("joe", new Date(), 3));
	}

	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	public List<User> findAll() {
		System.out.println("finding all users");
		return users;
	}

	public User findOne(int id) {

		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User deleteById(int id) {
		System.out.println("deleting in DAO");
		Iterator<User> it =users.iterator();
		while (it.hasNext()) {
			User user = it.next();
			if (user.getId() == id){
			it.remove();
			return user;
			}
		}
		return null;
	}
	
	
}
