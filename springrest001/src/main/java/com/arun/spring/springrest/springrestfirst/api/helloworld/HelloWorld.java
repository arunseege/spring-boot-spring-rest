package com.arun.spring.springrest.springrestfirst.api.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	public String getHelloWorld(){
	return "Hello World";	
	}

	@GetMapping(path="/hello-world-getmapping")
	public String getHelloWorldGetMapping(){
	return "Hello World getMapping()";	
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean getHelloWorldBean(){
	return new HelloWorldBean("hello-world-bean");	
	}
	
	@GetMapping(path="/hello-world-bean/pathvariable/{name}")
	public HelloWorldBean getHelloWorldBean(@PathVariable String name){
	return new HelloWorldBean(String.format("hello-world-bean for %s", name));	
	}

	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized(){
		return messageSource.getMessage("good.morning.message", null,LocaleContextHolder.getLocale());
	}
}
