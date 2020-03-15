package org.zju.adm.common.base;

/**
 * ClassName: BaseException
 * Description: 错误接口
 * Created by tiamo on 15/3/2020 10:47 上午
 */
public interface BaseError {

    int getErrorCode();
    String getErrMsg();
    BaseError setErrorMsg(String errMsg);
}
