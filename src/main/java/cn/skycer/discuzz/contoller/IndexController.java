package cn.skycer.discuzz.contoller;

import cn.skycer.discuzz.dto.QuestionDTO;
import cn.skycer.discuzz.mapper.QuestionMapper;
import cn.skycer.discuzz.mapper.UserMapper;
import cn.skycer.discuzz.model.Question;
import cn.skycer.discuzz.model.User;
import cn.skycer.discuzz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Johnny on 2019/8/3.
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions",questionList);
        return "index";
    }
}

