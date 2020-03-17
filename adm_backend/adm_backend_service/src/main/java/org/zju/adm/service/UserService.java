package org.zju.adm.service;

import org.zju.adm.pojo.Account;
import org.zju.adm.pojo.Users;
import org.zju.adm.pojo.bo.UserAccountBO;
import org.zju.adm.pojo.bo.UserBO;

/**
 * ClassName: UserService
 * Description: TODO
 * Created by tiamo on 15/3/2020 11:56 上午
 */
public interface UserService {

    boolean queryAccountIsExist(String account);

    UserBO userLogin(Account account) throws Exception;

    int insertUser(UserAccountBO userAccountBO);
}
