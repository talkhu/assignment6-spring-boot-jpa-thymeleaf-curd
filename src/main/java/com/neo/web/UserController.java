package com.neo.web;

import com.neo.model.CustomUser;
import com.neo.model.User;
import com.neo.security.IsEditor;
import com.neo.security.IsUser;
import com.neo.service.UserService;
import com.neo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@IsUser
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @RequestMapping("/list")
    @IsUser
    public String list(Authentication authentication, Model model) {
        List<User> users=userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }


    @RequestMapping("/toAdd")
    @IsEditor
    public String toAdd(Authentication authentication) {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    @IsEditor
    public String add(Authentication authentication,User user) {
        userService.save(user);
        System.out.println("add password: " + user.getPassword());
        return "redirect:/user/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,long id) {
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/user/list";
    }


    @RequestMapping("/delete")
    public String delete(long id) {
        userService.delete(id);
        return "redirect:/user/list";
    }
}
