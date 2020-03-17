package org.zju.adm.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.catalina.User;
import org.zju.adm.pojo.Account;
import org.zju.adm.pojo.Users;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * ClassName: AccountVO
 * Description: TODO
 * Created by tiamo on 17/3/2020 2:30 下午
 */
@ApiModel(value = "账号视图类", description = "用户账号")
public class AccountVO {

    @ApiModelProperty(name = "accountName", value = "账号名", required = true, example = "test")
    @NotBlank(message = "账户名不能为空")
    private String accountName;

    @ApiModelProperty(name = "password", value = "密码", required = true, example = "123456")
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account accountVOToAccount(){
        Account account = new Account();
        account.setAccountName(accountName);
        account.setPassword(password);
        return account;
    }
}
