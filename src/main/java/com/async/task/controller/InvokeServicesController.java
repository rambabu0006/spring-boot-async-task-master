package com.async.task.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.async.task.model.User;
import com.async.task.service.UserLookupService;

@RestController
@RequestMapping("/")
public class InvokeServicesController {

   private static final Logger LOGGER = LoggerFactory.getLogger(InvokeServicesController.class);

	@Autowired
	UserLookupService userLookupService;
	
	@GetMapping("invokeService")
	public void invokeServices() {
        LOGGER.info(" invokeServices:START current thread [" + Thread.currentThread().getName() + "]");
        long start = System.currentTimeMillis();
		try {
			// synch service
			User user = userLookupService.findUserNonAsynch("ram");
	        LOGGER.info("  Synch service "+user+" current thread [" + Thread.currentThread().getName() + "]");	

			// asynch services and wait until they are all done.
	        CompletableFuture<User> page1 = userLookupService.findUserAsynchAndComplete("ram");
	        CompletableFuture<User> page2 = userLookupService.findUserAsynchAndComplete("ramesh");

	        // Wait until they are all done
	        CompletableFuture.allOf(page1,page2).join();      
	        LOGGER.info("  Wait until they are all done "+page1.get()+page2.get()+" current thread [" + Thread.currentThread().getName() + "]");	

	        // assynch service
			HttpStatus  userHttpStatus = userLookupService.notifyUser("ram"); 
			// assynch service
			HttpStatus  empssHttpStatus =userLookupService.notifyEmp("ram");
	        LOGGER.info(" userHttpStatus "+userHttpStatus+" current thread [" + Thread.currentThread().getName() + "]");	
	        LOGGER.info(" empssHttpStatus "+empssHttpStatus+" current thread [" + Thread.currentThread().getName() + "]");	
		} catch (InterruptedException e) {			
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long end = System.currentTimeMillis();
        LOGGER.info(" invokeServices:END current thread [" + Thread.currentThread().getName() + "] execution time "+(end-start) );
        
	}
	
}
