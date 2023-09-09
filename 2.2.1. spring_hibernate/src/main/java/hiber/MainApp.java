package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        userService.add(new User("Petr", "Petrov", "Petr1@mail.ru", new Car("BMW", 1)));
        userService.add(new User("Ivan", "Ivanov", "Ivan2@mail.ru", new Car("Porsche", 2)));
        userService.add(new User("Turan", "Don", "user3@mail.ru", new Car("LADA", 2107)));

        users.clear();
        users = userService.listUsers();
        for (User user : users) {
            if (user.getCar() != null) {
                System.out.println("Id = " + user.getId());
                System.out.println("First Name = " + user.getFirstName());
                System.out.println("Last Name = " + user.getLastName());
                System.out.println("Email = " + user.getEmail());
                System.out.println(String.format("Car = %s %s", user.getCar().getModel(), user.getCar().getSeries()));
                System.out.println();
            }
        }

        User user = userService.findUserByCar("BMW", 1);
        System.out.println(String.format("User {name = %s, lastName =%s}", user.getFirstName(),user.getLastName()));


        context.close();
    }
}
