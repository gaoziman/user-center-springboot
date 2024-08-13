package org.javatop.usercenter.exception;

import lombok.extern.slf4j.Slf4j;
import org.javatop.usercenter.common.enums.HttpStatusEnum;
import org.javatop.usercenter.util.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-14 00:08
 * @description : 全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常
     *
     * @param request 请求
     * @param e       异常
     * @return Result
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result<String> exceptionHandler(HttpServletRequest request, BizException e) {
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getErrorCode(), e.getErrorMsg());
        return Result.fail(e.getErrorCode(), e.getErrorMsg());
    }


    /**
     * 处理空指针的异常
     *
     * @param request 请求
     * @param e       异常
     * @return Result
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result<String> exceptionHandler(HttpServletRequest request, NullPointerException e) {
        log.error(" {} 发生空指针异常！原因是:", e.getMessage());
        return Result.fail(HttpStatusEnum.BAD_REQUEST);
    }

    /**
     * 处理其他异常
     *
     * @param request 请求
     * @param e       异常
     * @return Result
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("发生未知异常！原因是:", e);
        return Result.fail(HttpStatusEnum.ERROR);
    }


    /**
     * 处理全局参数校验异常
     *
     * @param request 请求
     * @param e       exception
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Result<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        // 参数错误异常码
        Integer errorCode = HttpStatusEnum.PARAM_NOT_VALID.getErrorCode();

        // 获取 BindingResult
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder sb = new StringBuilder();

        // 获取校验不通过的字段，并组合错误信息，格式为： email 邮箱格式不正确, 当前值: '123124qq.com';
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(errors -> {
            errors.forEach(error ->
                    sb.append(error.getField())
                            .append(" ")
                            .append(error.getDefaultMessage())
                            .append(", 当前值: '")
                            .append(error.getRejectedValue())
                            .append("'; ")

            );
        });

        // 错误信息
        String errorMessage = sb.toString();

        log.warn("{} request error, errorCode: {}, errorMessage: {}", request.getRequestURI(), errorCode, errorMessage);

        return Result.fail(errorCode, errorMessage);
    }
}
