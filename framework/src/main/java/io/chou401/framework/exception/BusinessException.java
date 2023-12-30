package io.chou401.framework.exception;

/**
 * 业务异常
 *
 * {@code @author}  chou401
 * {@code @date} 2018-11-08
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
