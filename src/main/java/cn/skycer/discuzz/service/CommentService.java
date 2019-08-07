package cn.skycer.discuzz.service;

import cn.skycer.discuzz.enums.CommentTypeEnum;
import cn.skycer.discuzz.exception.CustomizeErrorCode;
import cn.skycer.discuzz.exception.CustomizeException;
import cn.skycer.discuzz.mapper.CommentMapper;
import cn.skycer.discuzz.mapper.QuestionExtMapper;
import cn.skycer.discuzz.mapper.QuestionMapper;
import cn.skycer.discuzz.model.Comment;
import cn.skycer.discuzz.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Johnny on 2019/8/7.
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    public void insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);

        }
        if(comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException((CustomizeErrorCode.TYPE_PARAM_WRONG));
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else {
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionExtMapper.incCommentCount(question);
        }
    }
}
