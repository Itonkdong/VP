package mk.ukim.finki.wpaud.web.controllers;

import mk.ukim.finki.wpaud.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ReturnTypesController
{

    @RequestMapping("/returnModelAndView")
    public ModelAndView method1()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User("user", "user"));
        modelAndView.setViewName("show-user");

        return modelAndView;
    }

     @RequestMapping("/returnView")
    public View method1(Model model)
    {
        model.addAttribute("user", new User("user", "user"));

        return new RedirectView("/showUser");
    }

//    @RequestMapping("/returnModel")
//    public Model showUser(Model model)
//    {
//        model.addAttribute("user", new User("user", "user"));
//
//        return new RedirectView("/showUser");
//    }
}
