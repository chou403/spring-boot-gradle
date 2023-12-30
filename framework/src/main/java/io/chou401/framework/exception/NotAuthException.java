package io.chou401.framework.exception;

/**
 * 没有权限异常
 *
 * {@code @author}  chou401
 * {@code @date} 2018-11-08
 */
public class NotAuthException extends BusinessException {

    public NotAuthException(String message) {
        super(message);
    }

}
