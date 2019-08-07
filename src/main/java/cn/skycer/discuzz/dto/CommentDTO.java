package cn.skycer.discuzz.dto;

import lombok.Data;

/**
 * Created by Johnny on 2019/8/7.
 */
@Data
public class CommentDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
