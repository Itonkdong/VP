package mk.ukim.finki.wpaud.web.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wpaud.service.ComplexInterface;
import mk.ukim.finki.wpaud.service.impl.ComplexInterfaceImpl1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller()
public class HomeController implements
        BeanNameAware, BeanFactoryAware, ApplicationContextAware,
        BeanPostProcessor, InitializingBean,
        DisposableBean
{


    private BeanFactory beanFactory;
    private String beanName;
    private ApplicationContext applicationContext;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String port;


    @Autowired
    private Map<String,ComplexInterface> allComplexInterfaces;


    private final ComplexInterface complexInterface;

//    @Autowired
    private final ClassaA a;

//    @Autowired
    public HomeController(ComplexInterfaceImpl1 complexInterface, ClassaA a)

    {
        this.complexInterface = complexInterface;
        this.a = a;
//        System.out.println("Home Controller (Bean) instantiated");
    }


    @Override
    public void setBeanName(String name)
    {
        this.beanName = name;
//        System.out.println("Home Controller (Bean): NAME AWARE");

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException
    {
        this.beanFactory = beanFactory;
//        System.out.println("Home Controller (Bean): BEAN FACTORY AWARE");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
//        System.out.println("Home Controller (Bean): APPLICATION CONTEXT AWARE");

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
//        System.out.println("POST PROCESS BEFORE INIT");

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
//        System.out.println("After properties set");
    }


    @PostConstruct
    public void init()
    {
//        System.out.println("INIT METHOD CALLED FROM HOME CONTROLLER");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {
//        System.out.println("POST PROCESS AFTER INIT");

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }


    @RequestMapping(method = RequestMethod.GET, path = {"/home"})
    public String showHome()
    {
        System.out.println(this.appName);
        System.out.println(this.port);
        this.complexInterface.process();

        for (String beanId : this.allComplexInterfaces.keySet())
        {
            this.allComplexInterfaces.get(beanId).process();
        }


        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String test(@RequestParam(required = true, value = "param1") String param, HttpServletRequest request)
    {
//        System.out.println(request.getHeader("User-agent"));
        return "index";
    }

    @PreDestroy
    public void lastCalledMethod()
    {
//        System.out.println("GOODBYE GOOD WORLD");
    }

    @Override
    public void destroy() throws Exception
    {
//        System.out.println("Destroying Home Controller (Bean)");
    }


}
