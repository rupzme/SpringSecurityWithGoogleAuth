package sample.application.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class HelloController {
 
	@RequestMapping(value ={"/"}, method = RequestMethod.GET)
    public String showHome(Model m) {
        
        return "welcome";
    }
	
	@RequestMapping(value ={"/accessDenied*"}, method = RequestMethod.GET)
    public String showAccessDenied(Model m) {
        m.addAttribute("name", "Access denied.");
        return "accessDenied";
    }
	
    @RequestMapping(value ={"/user**"}, method = RequestMethod.GET)
    public String showHomeUser(Model m) {
        m.addAttribute("name", "Hello User");
        return "user/helloUser";
    }
    
    @RequestMapping(value ={"/manager**"}, method = RequestMethod.GET)
    public String showHomeAdmin(Model m) {
        m.addAttribute("name", "Hello Manager");
        return "/manager/helloManager";
    }
}