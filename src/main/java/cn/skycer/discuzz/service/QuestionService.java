package cn.skycer.discuzz.service;

import cn.skycer.discuzz.dto.QuestionDTO;
import cn.skycer.discuzz.mapper.QuestionMapper;
import cn.skycer.discuzz.mapper.UserMapper;
import cn.skycer.discuzz.model.Question;
import cn.skycer.discuzz.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny on 2019/8/4.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public List<QuestionDTO> list() {
        List<Question> list = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question:list){
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
