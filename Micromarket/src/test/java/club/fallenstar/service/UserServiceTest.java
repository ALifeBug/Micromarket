package club.fallenstar.service;

import club.fallenstar.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mvc.xml"})
@WebAppConfiguration("src/main/resources")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setNickname("hqs2018");
        user.setPassword("123456");
        user.setEmail("3293971399@qq.com");
        user.setAcademy("计通");
        user.setGrade("大三");
        user.setPhone("18811503658");
        user.setContact("qq:3293971399");
        userService.save(user);
    }

    @Test
    public void update(){
        userService.edit("hqs2018","hqs2018","18811503658@163.com","大三","计通","qq:00000");
    }
}