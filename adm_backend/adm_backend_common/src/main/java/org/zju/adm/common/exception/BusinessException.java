package org.zju.adm.common.exception;

import org.zju.adm.common.base.BaseError;

/**
 * ClassName: BusinessException
 * Description: 业务异常类
 * Created by tiamo on 15/3/2020 11:07 上午
 */
public class BusinessException extends Exception implements BaseError {

    private BaseError baseError;

    public BusinessException(BaseError commonError){
        super();
        this.baseError = commonError;
    }

    public BusinessException(BaseError commonError, String errMsg){
        super();
        this.baseError = commonError;
        this.baseError.setErrorMsg(errMsg);
    }

    @Override
    public int getErrorCode() {
        return this.baseError.getErrorCode();
    }

    @Override
    public String getErrMsg() {
        return this.baseError.getErrMsg();
    }

    @Override
    public BaseError setErrorMsg(String errMsg) {
        return this.baseError.setErrorMsg(errMsg);
    }
}
