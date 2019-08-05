package cn.skycer.discuzz.model;

import lombok.Data;

/**
 * Created by Johnny on 2019/8/4.
 */
@Data
public class User {
    private Integer id;
    private String token;
    private String name;
    private String accountID;
    private long gmtCreate;
    private long gmtModified;
    private String avatarUrl;

}
