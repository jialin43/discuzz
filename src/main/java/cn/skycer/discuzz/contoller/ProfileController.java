package cn.skycer.discuzz.contoller;

import cn.skycer.discuzz.mapper.UserMapper;
import cn.skycer.discuzz.model.Question;
import cn.skycer.discuzz.model.User;
import cn.skycer.discuzz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Johnny on 2019/8/5.
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model) {
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        User user;
        user = (User) request.getSession().getAttribute("user");
        if (user.getAccountId() == null) {
            return "redirect:/";
        }
        List<Question> list = questionService.list(user.getAccountId());
        model.addAttribute("questions", list);
        return "profile";
    }

}
