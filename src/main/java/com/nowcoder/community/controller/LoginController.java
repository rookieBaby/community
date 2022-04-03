package com.nowcoder.community.controller;

import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import java.util.Map;

/**
 * @author Frank
 * @create 2022-04-02 16:58
 */
@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    private  UserService userService;

    @GetMapping("/login")
    public String getLoginPage(){
        return "/site/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "/site/register";
    }


    @PostMapping("/register")
    public String register(Model model, User user){
        Map<String, Object> map = userService.register(user);
        if (map == null || map.isEmpty()){//map为空  跳到首页,在激活，激活后才能注册
            model.addAttribute("msg", "注册成功,我们已经向您的邮箱发送了一封激活邮件,请尽快激活!");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        }else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "/site/register";
        }
    }


     // http://localhost:8080/community/activation/101/code
     @RequestMapping(path = "/activation/{userId}/{code}", method = RequestMethod.GET)
     public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code) {
         int result = userService.activation(userId, code);
         if (result == ACTIVATION_SUCCESS) {
             model.addAttribute("msg", "激活成功,您的账号已经可以正常使用了!");
             model.addAttribute("target", "/login");
         } else if (result == ACTIVATION_REPEAT) {
             model.addAttribute("msg", "无效操作,该账号已经激活过了!");
             model.addAttribute("target", "/index");
         } else {
             model.addAttribute("msg", "激活失败,您提供的激活码不正确!");
             model.addAttribute("target", "/index");
         }
         return "/site/operate-result";
     }








}
