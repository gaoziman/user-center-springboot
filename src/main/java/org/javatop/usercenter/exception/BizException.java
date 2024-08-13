package org.javatop.usercenter.exception;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-14 00:06
 * @description : 自定义业务异常类
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(BaseExceptionInterface errorInfoInterface) {
        super(errorInfoInterface.getErrorCode().toString());
        this.errorCode = errorInfoInterface.getErrorCode();
        this.errorMsg = errorInfoInterface.getErrorMessage();
    }

    public BizException(BaseExceptionInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getErrorCode().toString(), cause);
        this.errorCode = errorInfoInterface.getErrorCode();
        this.errorMsg = errorInfoInterface.getErrorMessage();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg) {
        super(errorCode.toString());
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg, Throwable cause) {
        super(errorCode.toString(), cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
