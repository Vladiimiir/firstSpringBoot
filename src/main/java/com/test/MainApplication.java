package com.test;
import com.test.exception.NotFoundException;
import com.test.exception.NotVerifiedUserException;
import com.test.model.Status;
import com.test.model.User;
import com.test.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws NotFoundException, NotVerifiedUserException {
        SpringApplication.run(MainApplication.class, args);

//        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
//        PhoneService phoneService = (PhoneService) context.getBean("phoneServiceImpl");
//        AddressService addressService = (AddressService) context.getBean("addressServiceImpl");
//        UserController controller = (UserController) context.getBean("userController") ;
//        UserService userService = (UserService) context.getBean("userServiceImpl");
//        User user = userService.getByEmailAndPassword("bob.bob@mail.ru", "bob123");
//        System.out.println(user.getStatus() == Status.UNVERIFIED);
//        System.out.println(userService.getByEmailAndPassword("bob.bob@mail.ru", "bob123"));
//        User user1 = new User("admin", "admin.admin@mail.com", "root");
//        user1.setId(1);
//        user1.setStatus(Status.VERIFIED);
//        user.setName("admin");
//        user.setEmail("admin.admin@gmail.com");
//        user.setPassword("root");
//        userService.update(user);
//        userService.getUsers()
//        User user = new User("Mike", "mike.hilson@mail.ru", "mike91");
//        user.setId(3);
//        System.out.println(userService.getAllByName("Bob"));
//        userService.getAll().forEach(System.out::println);
//        System.out.println(phoneService.getPhoneByModel("Iphone"));
//        Address address = addressService.getById(1);
//        System.out.println(address.getUsers());


    }
}