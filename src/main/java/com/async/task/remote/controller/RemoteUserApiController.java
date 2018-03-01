package com.async.task.remote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.async.task.SpringBootAsyncTaskApplication;
import com.async.task.model.User;
import com.async.task.service.RemoteUserApiService;

@RestController
public class RemoteUserApiController {

   private static final Logger LOGGER = LoggerFactory.getLogger(RemoteUserApiController.class);

	@Autowired
	RemoteUserApiService userApiService;	
	
	@GetMapping(value="/user/{user}")
	public User greetUser(@PathVariable("user") String userName) {
		User user = userApiService.getUser(userName);
		try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            LOGGER.info(" sleeping thread interrupted ");
        }
		return user;
	}
	
	@PostMapping(value="/nofityuser/{user}")
	public void notifyUser(@PathVariable("user") String userName) {
		try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            LOGGER.info(" sleeping thread interrupted ");
        }
	}
	
	@PostMapping(value="/notityemp/{user}")
	public void notifyEmployee(@PathVariable("user") String userName) {
		try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            LOGGER.info(" sleeping thread interrupted ");
        }
	}
}
