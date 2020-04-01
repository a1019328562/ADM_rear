package org.zju.adm.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.zju.adm.common.base.BaseError;

/**
 * ClassName: CommonError
 * Description: 通用的错误
 * Created by tiamo on 15/3/2020 10:53 上午
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CommonError implements BaseError {

    NO_SACH_TYPE_ERROR(30001,"无该参数类型"),
    PARAMETER_IS_NULL(30002,"参数为空"),
    DATATYPE_IS_EXIST(30003,"样本集已经存在"),
    DATATYPE_IS_NOT_EXIST(30004,"样本集不存在"),

    // 通用的错误类型
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),
    URL_BIND_ERROR(40003, "url绑定路由问题"),
    NO_HANDLER_ERROR(40004, "没有找到对应的访问路径"),
    MAX_UPLOAD_ERROR(40005, "文件大小超过限制"),
    OPERATION_ERROR(40006, "操作错误"),
    NO_PERMISSION_ERROR(40007, "您没有权限执行此操作"),
    ALGORITHM_OPERATION_ERROR(40008, "算法执行错误"),
    // 用户服务相关的错误类型
    ACCOUNT_NULL_ERROR(20001, "账户名不能为空"),
    ACCOUNT_EXISTS(20002, "账户名已存在"),
    ACCOUNT_PASSWORD_NOT_MATCH(20003, "账户名或密码不正确"),
    USER_SHOULD_LOGIN(20004, "用户尚未登录，请登录后再试试"),

    JWT_EXPIRE(20101, "登录信息已失效，请重新登录"),
    JWT_ERROR(20102, "登录验证错误，请重新登录"),
    JWT_ABNORMAL(20103, "登录异常，请重新登录"),
    JWT_NULL(20104, "无效登录信息，请重试"),
    ;

    private Integer errCode;

    private String errMsg;

    CommonError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrorCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
