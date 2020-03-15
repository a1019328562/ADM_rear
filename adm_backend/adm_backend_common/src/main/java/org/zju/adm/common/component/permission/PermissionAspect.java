package org.zju.adm.common.component.permission;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.zju.adm.common.CommonResult;
import org.zju.adm.common.constrant.GlobalConstant;
import org.zju.adm.common.exception.BusinessException;
import org.zju.adm.common.exception.CommonError;
import org.zju.adm.common.utils.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * ClassName: PermissionAspect
 * Description: TODO
 * Created by tiamo on 15/3/2020 3:56 下午
 */
@Aspect
@Configuration
public class PermissionAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        LoginPermission loginPermission = method.getAnnotation(LoginPermission.class);
        if(null == loginPermission){
            // 公共请求
            return joinPoint.proceed();
        }
        // 判断用户是否已登录
        String userId = "";
        String jwt = httpServletRequest.getHeader("User-Token");
        //判断jwt是否有效
        if(StringUtils.isNotBlank(jwt)){
            //校验jwt是否有效,有效则返回json信息，无效则返回空
            String retJson = JWTUtil.validateLogin(jwt);
            //retJSON为空则说明jwt超时或非法
            if(StringUtils.isNotBlank(retJson)){
                JSONObject jsonObject = JSONObject.parseObject(retJson);
                //校验客户端信息
                String userAgent = httpServletRequest.getHeader("User-Agent");
                if (userAgent.equals(jsonObject.getString("userAgent"))) {
                    //获取刷新后的jwt值，设置到响应头中
                    httpServletResponse.setHeader("User-Token", jsonObject.getString("freshToken"));
                    userId = jsonObject.getString("userId");
                    //将客户编号设置到session中
                    httpServletRequest.getSession().setAttribute(GlobalConstant.SESSION_USER_ID, userId);
                    return true;
                }else{
                    throw new BusinessException(CommonError.JWT_ERROR);
                }
            }else {
                throw new BusinessException(CommonError.JWT_NULL);
            }
        }
        if(StringUtils.isBlank(userId)){
            if(loginPermission.produceType().equals("text/html")){
                // 重定向到登录页面
                httpServletResponse.sendRedirect("/login.html");
                return null;
            } else{
                return CommonResult.failure(CommonError.NO_HANDLER_ERROR);
            }
        }else{
            return joinPoint.proceed();
        }


    }
}
