package mk.ukim.finki.wpaud.web.controllers;


import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wpaud.model.User;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("sessionUser")
public class RequestController
{
    private final ConversionService conversionService;

    public RequestController(ConversionService conversionService)
    {
        this.conversionService = conversionService;
    }

    @RequestMapping(path = "/form")
    public String showAddUser()
    {
        return "add-user.html";
    }

    @RequestMapping(path = "/showUser")
    public String showUser()
    {
        return "show-user.html";
    }

    //POST - 405 Method not allowed
    @RequestMapping(value = "/path/{*myPath}", method = RequestMethod.GET)
    public String getPath(@PathVariable String myPath, Model model)
    {

        model.addAttribute("tmp", myPath);
        return "mvc-test.html";
    }

    // Ne e consumed path varijablata
    @RequestMapping("/notConsumed/{*myPath}")
    public String method(String myPath, Model model)
    {

        model.addAttribute("tmp", myPath);
        return "mvc-test.html";
    }

    // Internal server Error - 500
    @RequestMapping("/unMatchedNames/{myPathVariable}")
    public String method1(@PathVariable(name = "myPathVariable1") String my, Model model)
    {

        model.addAttribute("tmp", my);
        return "mvc-test.html";
    }

    // Bez myName = 'neshto' - 400 error BadRequest
    @RequestMapping("/requiredTrue")
    public String method2(@RequestParam(required = true, name = "myName") String tmp, Model model)
    {

        model.addAttribute("tmp", tmp);
        return "mvc-test.html";
    }

    @RequestMapping("/headersAndCookies")
    public String method3(@RequestHeader(name = "User-Agent") String tmp, @CookieValue(name = "myCookie", required = false) String myCookie, Model model)
    {

        model.addAttribute("tmp", tmp);
        return "mvc-test.html";
    }

    @RequestMapping("/paramList")
    public String method4(@RequestParam Map<String, String> params, Model model)
    {

        model.addAttribute("tmp", params.get("myName"));
        return "mvc-test.html";
    }

    @RequestMapping("/convertPrice")
    public String method5(@RequestParam String price, Model model)
    {
        Double myPrice = this.conversionService.convert(price, Double.class);

        model.addAttribute("tmp", myPrice);
        return "mvc-test.html";
    }

    @ModelAttribute
    public void addCommonAttribute(Model model)
    {
        model.addAttribute("predefined", "PredefinedValue");
    }

    @ModelAttribute("defaultUser")
    public User getDefaultUser()
    {
        return new User("user", "user");
    }

//    @ModelAttribute("user")
//    @RequestMapping(path = "/advancedDefaultUser")
//    public User showUser()
//    {
//        return new User("advancedDefaultUser", "advancedDefaultUser");
//    }

    @RequestMapping("/defaultValue")
    public String method6(Model model)
    {

        model.addAttribute("tmp", model.getAttribute("predefined"));
        return "mvc-test.html";
    }

    @RequestMapping("/defaultUser")
    public String method7(Model model)
    {

        model.addAttribute("user", model.getAttribute("defaultUser"));
        return "show-user.html";
    }

    @RequestMapping("/addToSession")
    public String method8(Model model)
    {
        model.addAttribute("sessionUser", new User("session", "session"));
        return "mvc-test.html";
    }

    @RequestMapping("/showFromSession")
    public String method8(Model model, HttpSession httpSession)
    {
        model.addAttribute("user", httpSession.getAttribute("sessionUser"));
        return "show-user.html";

    }

    @RequestMapping("/completeSession")
    public String method8(Model model, SessionStatus sessionStatus)
    {
        sessionStatus.setComplete();
        return "mvc-test.html";
    }

    @RequestMapping("/showSessionAttribute")
    public String method9(Model model, @SessionAttribute User sessionUser)
    {
        model.addAttribute("user", sessionUser);
        return "show-user.html";

    }

    @RequestMapping("/showCustomUser")
    public String method10(Model model, @ModelAttribute User customUser)
    {
        model.addAttribute("user", customUser);
        return "show-user.html";

    }

    @RequestMapping("/showRequestUser")
    public String method11(Model model, @RequestAttribute User requestUser)
    {
        model.addAttribute("user", requestUser);
        return "show-user.html";

    }

    //Raboti so REST samo
    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public String method11(@RequestBody User user, Model model)
    {
        model.addAttribute("user", user);
        return "show-user.html";

    }

    @RequestMapping(path = "/addUserGeneral", method = RequestMethod.POST)
    public String method12(HttpEntity<User> user, Model model)
    {
        model.addAttribute("user", user);
        return "show-user.html";

    }

}
