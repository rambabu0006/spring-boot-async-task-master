# spring-boot-async-task-master

You.ll build a lookup service that queries  user information and retrieves data through other API. One approach to scaling services is to run expensive jobs in the background and wait for the results using Java.s CompletableFuture interface. Java.s CompletableFuture is an evolution from the regular Future. It makes it easy to pipeline multiple asynchronous operations merging them into a single asynchronous computation.



To build this service using maven

# mvn clean install

To run spring boot app
# mvn spring-boot:run -Dserver.port=9090

To trigger end point service use this URL:

http://localhost:9090/invokeService

We have done POC on Async rest service api calls. 

Controller: InvokeServicesController.java
                
This controller has invokeService() method, which consumes 4 rest api services.

	// 1 sync service
              User user = userLookupService.findUserNonAsynch("ram");
               LOGGER.info("  Synch service "+user+" current thread [" + Thread.currentThread().getName() + "]");  

              // 2 asyc services and wait until they are all done.
               CompletableFuture<User> page1 = userLookupService.findUserAsynchAndComplete("ram");
               CompletableFuture<User> page2 = userLookupService.findUserAsynchAndComplete("ramesh");

               // Wait until they are all done 
               CompletableFuture.allOf(page1,page2).join();      
               LOGGER.info("  Wait until they are all done "+page1.get()+page2.get()+" current thread [" + Thread.currentThread().getName() + "]"); 

               // 2 async services, we don.t wait until they are done
              HttpStatus  userHttpStatus = userLookupService.notifyUser("ram"); 
              HttpStatus  empssHttpStatus =userLookupService.notifyEmp("ram");



