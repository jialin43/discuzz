package cn.skycer.discuzz.service;

import cn.skycer.discuzz.dto.QuestionDTO;
import cn.skycer.discuzz.exception.CustomizeErrorCode;
import cn.skycer.discuzz.exception.CustomizeException;
import cn.skycer.discuzz.mapper.QuestionMapper;
import cn.skycer.discuzz.mapper.UserMapper;
import cn.skycer.discuzz.model.Question;
import cn.skycer.discuzz.model.QuestionExample;
import cn.skycer.discuzz.model.User;
import cn.skycer.discuzz.model.UserExample;
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
        //List<Question> list = questionMapper.list();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria();
        List<Question> list = questionMapper.selectByExample(questionExample);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question:list){
            //User user = userMapper.findByID(question.getCreator());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(question.getCreator().toString());
            List<User> users = userMapper.selectByExample(userExample);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(users.get(0));
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public List<Question> list(String accountID) {
        //List<Question> list = questionMapper.listByID(accountID);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(accountID);
        List<Question> list = questionMapper.selectByExample(questionExample);
        return list;
    }

    public QuestionDTO getByID(Integer id) {
        //Question question = questionMapper.findByID(id);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(id);
        List<Question> questions = questionMapper.selectByExample(questionExample);
        if(questions.size()==0){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(questions.get(0),questionDTO);
        //User user = userMapper.findByID(question.getCreator());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(questions.get(0).getCreator().toString());
        List<User> users = userMapper.selectByExample(userExample);
        questionDTO.setUser(users.get(0));
        return questionDTO;
    }
    public void CreateOrUpdate(Question question) {
        if(question.getId()==null){
            //create
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setReadCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            //questionMapper.create(question);
            questionMapper.insert(question);

        }else {
            //update
            question.setGmtModified(System.currentTimeMillis());
            //questionMapper.update(question);
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question,questionExample);
        }
    }

    public void incView(Integer id) {
        //Question question = questionMapper.findByID(id);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andIdEqualTo(id);
        List<Question> questions = questionMapper.selectByExample(example);
        if(questions.size()==1){
            Question updateQuestion = new Question();
            BeanUtils.copyProperties(questions.get(0),updateQuestion);
            updateQuestion.setReadCount(questions.get(0).getReadCount()+1);
            //questionMapper.update(updateQuestion);
            questionMapper.updateByExample(updateQuestion,example);
        }

    }
}
