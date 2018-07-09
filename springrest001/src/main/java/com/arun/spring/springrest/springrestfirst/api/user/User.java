package com.arun.spring.springrest.springrestfirst.api.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(description="all details about user")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id ;
	
	@Size(min=2,max=12,message="username should be atleast 2 characters")
	private String name;
	@Past
	private Date birthDate;
	
	
	
	
	
	
	public User(String name, Date birthDate, Integer id) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.id = id;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", birthDate=" + birthDate + ", id=" + id + ", getName()=" + getName()
				+ ", getBirthDate()=" + getBirthDate() + ", getId()=" + getId() +  "]";
	}
	
	
}
