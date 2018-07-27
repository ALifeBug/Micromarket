import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.DigestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mvc.xml"})
@WebAppConfiguration("src/main/resources")
public class md5Test {

    @Test
    public void test(){
        String str = DigestUtils.md5DigestAsHex("12345".getBytes());
        System.out.println("md5Hex:     "+str);
    }
}
