package com.async.task.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.async.task.model.User;
/**
 * This service is triggering Remote Rest calls
 * @author rambabum
 *
 */
@Service
public class UserLookupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLookupService.class);

    private final RestTemplate restTemplate;

    public UserLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    
    public User findUserNonAsynch(String user) throws InterruptedException {
        LOGGER.info(" findUserNonAsynch:START current thread [" + Thread.currentThread().getName() + "]");
        //String url = String.format("https://api.github.com/users/%s", user);
        String url = String.format("http://localhost:9090/user/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        LOGGER.info(" findUserNonAsynch:END current thread [" + Thread.currentThread().getName() + "]");
        return results;
    }

    
    @Async
    public CompletableFuture<User> findUserAsynchAndComplete(String user) throws InterruptedException {
        LOGGER.info(" findUserAsynchAndComplete:START current thread [" + Thread.currentThread().getName() + "]");
        String url = String.format("http://localhost:9090/user/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        LOGGER.info(" findUserAsynchAndComplete:END current thread [" + Thread.currentThread().getName() + "]");
        return CompletableFuture.completedFuture(results);
    }

    @Async
    public HttpStatus notifyUser(String user) throws InterruptedException {
        LOGGER.info(" notifyUser: current thread [" + Thread.currentThread().getName() + "]");
        String url = String.format("http://localhost:9090/nofityuser/%s", user);        
		ResponseEntity<HttpStatus> response = restTemplate.postForEntity(url, String.class, HttpStatus.class);
        LOGGER.info(" notifyUser: completed [" + Thread.currentThread().getName() + "]");
		return response.getBody();
    }
    
    @Async
    public HttpStatus notifyEmp(String user) throws InterruptedException {
        LOGGER.info(" notifyEmp: current thread [" + Thread.currentThread().getName() + "]");
        String url = String.format("http://localhost:9090/notityemp/%s", user);
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(url, String.class, HttpStatus.class);
        LOGGER.info(" notifyEmp: completed [" + Thread.currentThread().getName() + "]");
		return response.getBody();
    }
    
    

}
