package club.fallenstar.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CookieUtils {

    //添加cookie
    public  static void cookie(int autoLoginTimeout, HttpServletResponse response, String name, String password) throws UnsupportedEncodingException {
        Cookie userNameCookie = new Cookie("userName",URLEncoder.encode(name,"utf-8"));
        Cookie passwordCookie = new Cookie("password",password);
        userNameCookie.setMaxAge(autoLoginTimeout);
        userNameCookie.setPath("/");
        passwordCookie.setMaxAge(autoLoginTimeout);
        passwordCookie.setPath("/");
        response.addCookie(userNameCookie);
        response.addCookie(passwordCookie);
    }
}
