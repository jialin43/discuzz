package cn.skycer.discuzz.mapper;

import cn.skycer.discuzz.dto.QuestionDTO;
import cn.skycer.discuzz.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Johnny on 2019/8/4.
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from question")
    List<Question> list();

    @Select("select * from question where creator=#{accountID}")
    List<Question> listByID(@Param("accountID") String accountID);

    @Select("select * from question where id=#{id}")
    Question findByID(@Param("id") Integer id);

    @Update("update question set title=#{title},description=#{description},read_count=#{readCount},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}")
    void update(Question question);
}
