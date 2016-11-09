package configuration;

import dao.CarsDao;
import dao.CarsDaoJdbcImpl;
import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.CarService;
import services.CarServiceJdbcImpl;
import services.UserService;
import services.UserServiceJdbcImpl;

@Configuration
public class JavaConfiguration {
//    @Bean
//    public UserService userService() {
//        return new UserServiceJdbcImpl(usersDao());
//    }
//    @Bean
//    public UsersDao usersDao() {
//        return new UsersDaoJdbcImpl();
//    }
//    @Bean
//    public CarService carService() {
//        return new CarServiceJdbcImpl(carsDao());
//    }
//    @Bean
//    public CarsDao carsDao() {
//        return new CarsDaoJdbcImpl();
//    }
}
