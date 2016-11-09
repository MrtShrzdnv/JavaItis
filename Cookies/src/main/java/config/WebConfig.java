package config;

import dao.CarsDao;
import dao.CarsDaoJdbcImpl;
import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import services.CarService;
import services.CarServiceJdbcImpl;
import services.UserService;
import services.UserServiceJdbcImpl;

import javax.sql.DataSource;

/**
 * Created by Marat_2 on 09.11.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan("controllers")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver configureViewResolver() {
        InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
        viewResolve.setPrefix("/WEB-INF/views/");
        viewResolve.setSuffix(".jsp");

        return viewResolve;
    }
    @Bean(name = "dataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgresql");
        return dataSource;
    }
    @Bean
    public UserService userService() {
        return new UserServiceJdbcImpl(usersDao());
    }
    @Bean
    public UsersDao usersDao() {
        return new UsersDaoJdbcImpl(dataSource());
    }
    @Bean
    public CarService carService() {
        return new CarServiceJdbcImpl(carsDao());
    }
    @Bean
    public CarsDao carsDao() {
        return new CarsDaoJdbcImpl(dataSource());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
