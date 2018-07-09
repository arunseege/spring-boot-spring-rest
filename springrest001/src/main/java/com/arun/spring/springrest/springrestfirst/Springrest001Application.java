package com.arun.spring.springrest.springrestfirst;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@ComponentScan({"com.arun.spring.springrest.springrestfirst.versioning","com.in28minutes.rest.webservices.restfulwebservices.filtering","com.arun.spring.springrest.springrestfirst.api.helloworld","com.arun.user.dao","com.arun.spring.springrest.springrestfirst.api.user","com.arun.spring.springrest.springrestfirst.helpers","com.arun.user.beans"})
@SpringBootApplication
public class Springrest001Application {

	public static void main(String[] args) {
		SpringApplication.run(Springrest001Application.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}
	
	//resourcebundlemessagesource -it will read properties and
	//customize based on input accept header
	/*@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
		bundleMessageSource.setBasename("messages");
		return bundleMessageSource;
	}	*/
	
}
