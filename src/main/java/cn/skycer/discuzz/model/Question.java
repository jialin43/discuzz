package cn.skycer.discuzz.model;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by Johnny on 2019/8/4.
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private String creator;
    private Integer readCount;
    private Integer commentCount;
    private Integer likeCount;

}
