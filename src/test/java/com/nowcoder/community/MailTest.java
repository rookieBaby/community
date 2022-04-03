package com.nowcoder.community;



import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


/**
 * @author Frank
 * @create 2022-04-02 15:38
 */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTest {

    @Autowired
    MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;//模板引擎
    @Test
    public void testMail(){
          mailClient.sendMail("980699149@qq.com","Text","Hello~");
    }



    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "sunday");

        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);


        mailClient.sendMail("980699149@qq.com", "HTML", content);
    }



}
