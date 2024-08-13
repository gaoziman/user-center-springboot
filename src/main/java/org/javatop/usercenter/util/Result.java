package org.javatop.usercenter.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javatop.usercenter.common.enums.HttpStatusEnum;
import org.javatop.usercenter.exception.BaseExceptionInterface;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-14 00:05
 * @description : 统一的返回结果
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private Boolean status;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 全参数方法
     *
     * @param code    状态码
     * @param status  状态
     * @param message 返回信息
     * @param data    返回数据
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    private static <T> Result<T> response(Integer code, Boolean status, String message, T data) {
        Result<T> Result = new Result<>();
        Result.setCode(code);
        Result.setStatus(status);
        Result.setMessage(message);
        Result.setData(data);
        return Result;
    }


    /**
     * 全参数方法 (异常接口)
     *
     * @param baseExceptionInterface 异常接口
     */
    public Result(BaseExceptionInterface baseExceptionInterface) {
        this.code = Integer.valueOf(baseExceptionInterface.getErrorCode());
        this.message = baseExceptionInterface.getErrorMessage();
    }

    /**
     * 全参数方法
     *
     * @param code    状态码
     * @param status  状态
     * @param message 返回信息
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    private static <T> Result<T> response(Integer code, Boolean status, String message) {
        Result<T> Result = new Result<>();
        Result.setCode(code);
        Result.setStatus(status);
        Result.setMessage(message);
        return Result;
    }

    /**
     * 成功返回（无参）
     *
     * @param <T> 泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> success() {
        return response(HttpStatusEnum.SUCCESS.getCode(), true, HttpStatusEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回（枚举参数）
     *
     * @param httpResponseEnum 枚举参数
     * @param <T>              泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> success(HttpStatusEnum httpResponseEnum) {
        return response(httpResponseEnum.getCode(), true, httpResponseEnum.getMessage());
    }

    /**
     * 成功返回（状态码+返回信息）
     *
     * @param code    状态码
     * @param message 返回信息
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> success(Integer code, String message) {
        return response(code, true, message);
    }

    /**
     * 成功返回（返回信息 + 数据）
     *
     * @param message 返回信息
     * @param data    数据
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> success(String message, T data) {
        return response(HttpStatusEnum.SUCCESS.getCode(), true, message, data);
    }

    /**
     * 成功返回（状态码+返回信息+数据）
     *
     * @param code    状态码
     * @param message 返回信息
     * @param data    数据
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> success(Integer code, String message, T data) {
        return response(code, true, message, data);
    }

    /**
     * 成功返回（数据）
     *
     * @param data 数据
     * @param <T>  泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> success(T data) {
        return response(HttpStatusEnum.SUCCESS.getCode(), true, HttpStatusEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回（返回信息）
     *
     * @param message 返回信息
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> success(String message) {
        return response(HttpStatusEnum.SUCCESS.getCode(), true, message, null);
    }

    /**
     * 失败返回（无参）
     *
     * @param <T> 泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> fail() {
        return response(HttpStatusEnum.ERROR.getCode(), false, HttpStatusEnum.ERROR.getMessage(), null);
    }

    /**
     * 失败返回（枚举）
     *
     * @param httpResponseEnum 枚举
     * @param <T>              泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> fail(HttpStatusEnum httpResponseEnum) {
        return response(httpResponseEnum.getCode(), false, httpResponseEnum.getMessage());
    }

    /**
     * 失败返回（状态码+返回信息）
     *
     * @param code    状态码
     * @param message 返回信息
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> fail(Integer code, String message) {
        return response(code, false, message);
    }

    /**
     * 失败返回（返回信息+数据）
     *
     * @param message 返回信息
     * @param data    数据
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> fail(String message, T data) {
        return response(HttpStatusEnum.ERROR.getCode(), false, message, data);
    }

    /**
     * 失败返回（状态码+返回信息+数据）
     *
     * @param code    状态码
     * @param message 返回消息
     * @param data    数据
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> fail(Integer code, String message, T data) {
        return response(code, false, message, data);
    }

    /**
     * 失败返回（数据）
     *
     * @param data 数据
     * @param <T>  泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> fail(T data) {
        return response(HttpStatusEnum.ERROR.getCode(), false, HttpStatusEnum.ERROR.getMessage(), data);
    }

    /**
     * 失败返回（返回信息）
     *
     * @param message 返回信息
     * @param <T>     泛型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> fail(String message) {
        return response(HttpStatusEnum.ERROR.getCode(), false, message, null);
    }


    /**
     * 异常返回 (异常接口)
     *
     * @param exceptionInterface 异常接口
     * @return {@link Result}
     */
    public static <T> Result<T> fail(BaseExceptionInterface exceptionInterface) {
        Result<T> result = new Result<T>();
        result.setCode(exceptionInterface.getErrorCode());
        result.setMessage(exceptionInterface.getErrorMessage());
        result.setData(null);
        return result;
    }
}
