package com.alexahdp;

import com.alexahdp.spring.service.UserService;
import com.alexahdp.spring.database.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        UserService userService = context.getBean("userService", UserService.class);
        userService.sayHello(1);

        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
        userRepository.findAll();
//        System.out.println("SUCCESS");
//        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        userService.sayHello();
//        System.out.println("SUCCESS");
    }

//    public static void main0(String[] args) {
//        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        userService.sayHello();
//        System.out.println("SUCCESS");
//    }

//    public static void main1(String[] args) {
//        var list = new ArrayList<Integer>(Arrays.asList(1,2,3));
//        var list = new ArrayList<Integer>(Arrays.asList(1,2,3);
//    }
}
