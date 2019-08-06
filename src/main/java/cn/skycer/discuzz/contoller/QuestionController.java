package cn.skycer.discuzz.contoller;

import cn.skycer.discuzz.dto.QuestionDTO;
import cn.skycer.discuzz.mapper.QuestionMapper;
import cn.skycer.discuzz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Johnny on 2019/8/5.
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){
        //累加阅读数
        questionService.incView(id);
        QuestionDTO questionDTO = questionService.getByID(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
