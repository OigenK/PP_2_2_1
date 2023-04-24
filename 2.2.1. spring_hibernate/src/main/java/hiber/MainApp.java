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
      Car car1 = new Car("bmv",3);
      Car car2 = new Car("audi",5);
      Car car3 = new Car("toyota",12);
      Car car4 = new Car("vaz",15);
      userService.addCar(car1);
      userService.addCar(car2);
      userService.addCar(car3);
      userService.addCar(car4);

      userService.add(new User("Vasya", "Popov", "Pop@mail.ru",car2));
      userService.add(new User("Gora", "Sidorov", "Gor@mail.ru",car4));
      userService.add(new User("Masha", "Oporrov", "Opo@mail.ru",car3));
      userService.add(new User("Vova", "Ketsko", "KEt@mail.ru",car1));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.toString() + "$$$$$$$$$$$$$$");

      }
      List<Car> cars = userService.listCars();
      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("First Name = "+car.getModel());
         System.out.println("Last Name = "+car.getSeries());
         System.out.println();
      }
      System.out.println("----------------------------------------------");
      System.out.println(userService.getCar("vaz",15));


      context.close();
   }
}
