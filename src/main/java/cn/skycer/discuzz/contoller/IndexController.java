package cn.skycer.discuzz.contoller;

import cn.skycer.discuzz.mapper.UserMapper;
import cn.skycer.discuzz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Johnny on 2019/8/3.
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies !=null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }

            }
        }
        return "index";
    }
}

