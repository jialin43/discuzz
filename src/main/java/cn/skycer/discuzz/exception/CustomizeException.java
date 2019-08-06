package cn.skycer.discuzz.exception;

/**
 * Created by Johnny on 2019/8/5.
 */
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
