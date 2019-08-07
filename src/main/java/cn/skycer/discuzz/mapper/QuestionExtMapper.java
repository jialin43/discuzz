package cn.skycer.discuzz.mapper;

import cn.skycer.discuzz.model.Question;

/**
 * Created by Johnny on 2019/8/7.
 */
public interface QuestionExtMapper {
    int incCommentCount(Question question);
}
