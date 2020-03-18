package org.zju.adm.mapper;

import org.zju.adm.common.base.BaseMapper;
import org.zju.adm.pojo.Account;
import org.zju.adm.pojo.bo.UserAccountBO;

public interface AccountMapper extends BaseMapper<Account> {
    int insertReturnPK(Account account);
}