package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

/**
 * @author Frank
 * @create 2022-04-01 19:51
 */
@ComponentScan("com.nowcoder.community.dao")
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    DiscussPostMapper discussPostMapper;


    @Test
    public void testSelect(){
        User user = userMapper.selectById(101);
        System.out.println(user);

        System.out.println(userMapper.selectByName("liubei"));
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser() {
        int rows = userMapper.updateStatus(150, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        System.out.println(rows);

        userMapper.updatePassword(150,"admin");
        System.out.println(rows);
    }

    @Test
    public void testDiscussPost(){
        int i = discussPostMapper.selectDiscussPostRows(149);
        System.out.println("讨论贴的数量是："+i);

        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 8);
        for (DiscussPost post:list){
            System.out.println(post);
        }


    }



}
