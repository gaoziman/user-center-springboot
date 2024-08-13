package org.javatop.usercenter.exception;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-14 00:05
 * @description : 通用异常接口
 */
public interface BaseExceptionInterface {
    Integer getErrorCode();

    String getErrorMessage();
}
