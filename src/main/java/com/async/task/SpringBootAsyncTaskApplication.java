package com.async.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAsyncTaskApplication {
//public class SpringBootAsyncTaskApplication implements CommandLineRunner {

    //private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootAsyncTaskApplication.class);

/* 
 *  @Autowired
    private WorkerService workerService;
*/
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAsyncTaskApplication.class, args);
    }

  /*  @Override
    public void run(String... strings) throws Exception
    {
        if (strings == null || strings.length == 0) {
            LOGGER.error(" worker type is expected as the command line parameter ");
            return;
        }

        if (strings[0].equals("non-async")) {
            workerService.runNonAsyncWorkers();
        }
        else if (strings[0].equals("async")) {
            workerService.runAsyncWorkers();
        }
        else if (strings[0].equals("future")) {
            workerService.runAsyncWorkersWithFutureReturnType();
        }
    }*/
}
