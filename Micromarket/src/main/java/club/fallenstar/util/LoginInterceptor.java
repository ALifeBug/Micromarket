package club.fallenstar.util;

import club.fallenstar.entity.User;
import club.fallenstar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by sccy on 2018/4/3/0003.
 */
public class LoginInterceptor implements HandlerInterceptor{

    @Autowired
    private UserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object sessionuser = request.getSession().getAttribute("user");
        if(sessionuser == null){
            String loginCookieUserName = "";
            String loginCookiePassword = "";

            Cookie[] cookies = request.getCookies();
            if(null!=cookies){
                for(Cookie cookie : cookies){
                    if("userName".equals(cookie.getName())){
                        loginCookieUserName = URLDecoder.decode(cookie.getValue(),"utf-8");
                    }else if("password".equals(cookie.getName())){
                        loginCookiePassword = cookie.getValue();
                    }
                }
                if(!"".equals(loginCookieUserName) && !"".equals(loginCookiePassword)){
                    User user = userService.findByName(loginCookieUserName);
                    if(user!=null && loginCookiePassword.equals(user.getPassword())){
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        }
        return true;
    }



    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
}
