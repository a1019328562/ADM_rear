package org.zju.adm.api.config;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.zju.adm.common.CommonResult;
import org.zju.adm.common.exception.BusinessException;
import org.zju.adm.common.exception.CommonError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: GlobalExceptionHandler
 * Description: 全局异常捕获类
 * Created by tiamo on 15/3/2020 11:04 上午
 */
@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public CommonResult doError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Exception ex){
        ex.printStackTrace();
        Map<String, Object> responseData = new HashMap<>();
        if(ex instanceof BusinessException){
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode", businessException.getErrorCode());
            responseData.put("errMsg", businessException.getErrMsg());
        }else if(ex instanceof ServletRequestBindingException){
            responseData.put("errCode", CommonError.URL_BIND_ERROR.getErrorCode());
            responseData.put("errMsg", CommonError.URL_BIND_ERROR.getErrMsg());
        }else if(ex instanceof NoHandlerFoundException){
            responseData.put("errCode", CommonError.NO_HANDLER_ERROR.getErrorCode());
            responseData.put("errMsg", CommonError.NO_HANDLER_ERROR.getErrMsg());
        }else if(ex instanceof MaxUploadSizeExceededException){
            responseData.put("errCode", CommonError.MAX_UPLOAD_ERROR.getErrorCode());
            responseData.put("errMsg", CommonError.MAX_UPLOAD_ERROR.getErrMsg());
        }else{
            responseData.put("errCode", CommonError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg", CommonError.UNKNOWN_ERROR.getErrMsg());
        }
        return CommonResult.failure(responseData);
    }
}
