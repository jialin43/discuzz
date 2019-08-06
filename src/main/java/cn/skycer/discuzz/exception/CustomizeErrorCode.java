package cn.skycer.discuzz.exception;

/**
 * Created by Johnny on 2019/8/5.
 */
public enum  CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("啊偶,出错啦");
    @Override
    public String getMessage(){
        return message;
    }
    private String message;
    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
