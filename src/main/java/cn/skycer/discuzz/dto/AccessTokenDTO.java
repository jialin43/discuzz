package cn.skycer.discuzz.dto;

import lombok.Data;

/**
 * Created by Johnny on 2019/8/3.
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
