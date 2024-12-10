package mk.ukim.finki.wpaud.configuration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import mk.ukim.finki.wpaud.service.CategoriesService;
import mk.ukim.finki.wpaud.service.impl.CategoriesServiceImpl;
import mk.ukim.finki.wpaud.web.controllers.HomeController;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class AppConfig
{

//    public static void main(String[] args)
//    {
//        AnnotationConfigApplicationContext javaCodeSpringContainer = new AnnotationConfigApplicationContext(AppConfig.class);
//        ClassPathXmlApplicationContext xmlSprintContainer = new ClassPathXmlApplicationContext("D:\\Fax\\VP\\wp-aud\\src\\main\\resources\\applicationContext.xml");
//
//        HomeController bean = javaCodeSpringContainer.getBean(HomeController.class);
//        HomeController bean1 = xmlSprintContainer.getBean(HomeController.class);
//    }

    // Drug nachin za kreiranje na bin, preku konfiguracija
//    @Bean(initMethod = "init", destroyMethod = "lastCalledMethod")
//    public HomeController homeController(CategoriesServiceImpl categoriesServiceimpl)
//    {
//        return new HomeController(categoriesServiceimpl);
//    }
}
