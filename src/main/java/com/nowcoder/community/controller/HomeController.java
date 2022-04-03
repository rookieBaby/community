package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Frank
 * @create 2022-04-01 21:39
 */
@Controller
public class HomeController {

    @Autowired
    DiscussPostService discussPostService;

    @Autowired
    UserService userService;

    @GetMapping("/index")
   public String getIndexPage(Model model, Page page){
        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(discussPostService.findDisCussPostRow(0));
        page.setPath("/index");

        //查询所有的帖子
        List<DiscussPost> list = discussPostService.findDiscussPost(0, page.getOffset(), page.getLimit());
        //遍历每一个帖子，根据每个帖子的user_id 查询出对应的user对象b,
        // 并将当前遍历出的帖子和根据userId查询的user对象放在一个map中,再把当前map放入list集合(discussPost)中，通过model进行数据共享，让index页面可以使用
        List<Map<String,Object>> discussPost = new ArrayList<>();
        if (list != null){
            for (DiscussPost post:list){
                HashMap<String, Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPost.add(map);
            }
        }
        model.addAttribute("discussPost",discussPost);
        return "index";
    }


}
