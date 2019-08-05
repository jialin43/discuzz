package cn.skycer.discuzz.dto;


import cn.skycer.discuzz.model.User;
import lombok.Data;

/**
 * Created by Johnny on 2019/8/4.
 */
@Data
public class QuestionDTO {
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
    private User user;
}
