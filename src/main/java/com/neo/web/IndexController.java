package com.neo.web;

import com.neo.model.User;
import com.neo.service.UserService;
import com.neo.service.UserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    UserService userService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String index() {
        List<User> users=userService.getUserList();
        if (users.isEmpty()) {
            userServiceImpl.UserInit();
        }
        return "index/index";

    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        List<User> users=userService.getUserList();
        if (users.isEmpty()) {
            userServiceImpl.UserInit();
        }
        return "index/login";
    }

//   @GetMapping("/toRegister")
//    public ModelAndView toRegister() {
//        ModelAndView mv = new ModelAndView("index/register");
//        return mv;
//    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "index/register";
    }

    @RequestMapping("/register")
    public String register(Authentication authentication,User user) {
        userService.save(user);
        return "redirect:/registerSuc";
    }

    @GetMapping("/registerSuc")
    public String registerSuc() {
        return "index/registerSuc";
    }


    @GetMapping("/logout")
    public String logout() {
        return "index/logout";
    }
}
