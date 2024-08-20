package org.javatop.usercenter.common.enums;

import lombok.Getter;
import org.javatop.usercenter.exception.BaseExceptionInterface;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-14 00:04
 * @description : HttpStatus枚举类
 */
@Getter
public enum HttpStatusEnum implements BaseExceptionInterface {

    SUCCESS(200, "操作成功"),

    NO_CONTENT(204, "操作执行成功，无返回数据"),

    MOVED_PERM(301, "资源已被移除"),

    SEE_OTHER(303, "重定向"),

    NOT_MODIFIED(304, "资源没有被修改"),

    BAD_REQUEST(400, "参数列表错误（缺少，格式不匹配）"),

    UNAUTHORIZED(401, "未授权"),

    FORBIDDEN(403, "访问受限，授权过期"),

    NOT_FOUND(404, "资源，服务未找！"),

    BAD_METHOD(405, "不允许的http方法"),

    CONFLICT(409, "资源冲突，或者资源被锁"),

    UNSUPPORTED_TYPE(415, "不支持的数据，媒体类型"),

    ERROR(500, "系统内部错误"),

    PARAM_NOT_VALID(701,"参数校验失败"),



    USERNAME_OR_PWD_ERROR(20001, "用户名或密码错误"),

    LOGIN_FAIL(20000, "登录失败,请稍后再试"),


    USERNAME_NOT_FOUND(20004, "用户名不存在！"),

    ARGS_NOT_NULL(20005, "参数不能为空"),

    USERNAME_EXIST(20006, "用户名已存在！"),

    PASSWORD_NOT_MATCH(20007, "两次密码输入不一致！"),

    FILE_UPLOAD_FAILED(20008, "文件上传失败！");


    private final Integer code;
    private final String message;

    HttpStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}