package org.zju.adm.api.controller;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zju.adm.common.CommonResult;
import org.zju.adm.common.component.validator.ValidationResult;
import org.zju.adm.common.component.validator.ValidatorBean;
import org.zju.adm.common.exception.BusinessException;
import org.zju.adm.common.exception.CommonError;
import org.zju.adm.pojo.Account;
import org.zju.adm.pojo.Data;
import org.zju.adm.pojo.UserType;
import org.zju.adm.pojo.Users;
import org.zju.adm.pojo.bo.UserAccountBO;
import org.zju.adm.pojo.bo.UserBO;
import org.zju.adm.pojo.vo.AccountVO;
import org.zju.adm.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: UserController
 * Description: 用户服务相关接口
 * Created by tiamo on 15/3/2020 10:58 上午
 */
@Api(value = "注册登录", tags = {"用户相关接口"})
@RestController
@RequestMapping("/passport")
public class PassportController {


    @Autowired
    private UserService userService;

    @Autowired
    private ValidatorBean validatorBean;

    @ApiOperation(value = "账户名是否存在", notes = "账户名是否存在", httpMethod = "GET")
    @ApiImplicitParam(name = "accountName", value = "账户名", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/accountIsExist", method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
    public CommonResult accountIsExist(
            @RequestParam String accountName) {
        // 1. 判断入参不能为空
        if (StringUtils.isBlank(accountName)) {
            return CommonResult.failure(CommonError.ACCOUNT_NULL_ERROR);
        }
        // 2. 查找账号是否存在
        boolean isExist = userService.queryAccountIsExist(accountName);
        if (isExist) {
            return CommonResult.failure(CommonError.ACCOUNT_EXISTS);
        }
        // 3. 校验成功
        return CommonResult.success();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult login(
            @RequestBody AccountVO account,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ValidationResult validationResult = validatorBean.validate(account);
        if(validationResult.isHasErrors()){
            throw new BusinessException(CommonError.PARAMETER_VALIDATION_ERROR, validationResult.getErrMsg());
        }
        UserBO result = userService.userLogin(account.accountVOToAccount());
        if(null == result) {
            return CommonResult.failure(CommonError.ACCOUNT_PASSWORD_NOT_MATCH);
        }
        return CommonResult.success(result);
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult register(@RequestBody UserAccountBO userAccountBO) throws Exception {
        Account account = new Account();
        BeanUtils.copyProperties(userAccountBO,account);
        ValidationResult validationResult = validatorBean.validate(account);
        if(validationResult.isHasErrors()){
            throw new BusinessException(CommonError.PARAMETER_VALIDATION_ERROR, validationResult.getErrMsg());
        }
        boolean isExist = userService.queryAccountIsExist(account.getAccountName());
        if(isExist){
            return CommonResult.failure(CommonError.ACCOUNT_EXISTS);
        }
        int result = userService.insertUser(userAccountBO);
        if(result<=0){
            return CommonResult.failure(CommonError.PARAMETER_VALIDATION_ERROR);
        }
        return CommonResult.success(userAccountBO);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @RequestMapping(value = "/editUserProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult editUserProfile(@RequestBody Users users){
        boolean userTypeIsExist = userService.queryUserTypeIsExist(users.getUserType());
        if(!userTypeIsExist){
            return CommonResult.failure(CommonError.PARAMETER_VALIDATION_ERROR);
        }
        userService.editUserProfile(users);
        return CommonResult.success(users);
    }

    @ApiOperation(value = "获取用户", notes = "获取用户", httpMethod = "GET")
    @RequestMapping(value = "/selectAllUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult selectAllUser(){
        List<Users> result = userService.selectAllUsers();
        return CommonResult.success(result);
    }
}
