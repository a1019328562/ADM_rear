package org.zju.adm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zju.adm.common.enums.Gender;
import org.zju.adm.common.utils.CommonUtil;
import org.zju.adm.common.utils.JWTUtil;
import org.zju.adm.mapper.AccountMapper;
import org.zju.adm.mapper.UserTypeMapper;
import org.zju.adm.mapper.UsersMapper;
import org.zju.adm.pojo.Account;
import org.zju.adm.pojo.UserType;
import org.zju.adm.pojo.Users;
import org.zju.adm.pojo.bo.UserAccountBO;
import org.zju.adm.pojo.bo.UserBO;
import org.zju.adm.service.UserService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Description: TODO
 * Created by tiamo on 15/3/2020 11:56 上午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserTypeMapper userTypeMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryAccountIsExist(String accountName) {
        Example accountExample = new Example(Account.class);
        Example.Criteria accountCriteria = accountExample.createCriteria();
        accountCriteria.andEqualTo("accountName", accountName);
        Account account = accountMapper.selectOneByExample(accountExample);
        return account != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserBO userLogin(Account account) throws Exception {
        // 先判断账号是否存在，然后返回用户信息
        Example accountExample = new Example(Account.class);
        Example.Criteria accountCriteria = accountExample.createCriteria();
        accountCriteria.andEqualTo("accountName", account.getAccountName());
        accountCriteria.andEqualTo("password", CommonUtil.getMD5Digest(account.getPassword()));
        Account loginAccount = accountMapper.selectOneByExample(accountExample);
        if(null==loginAccount){
            return null;
        }

        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("accountId", loginAccount.getId());
        Users user = usersMapper.selectOneByExample(userExample);
        if(null == user){
            return null;
        }

        // 获取用户类型
        UserType userType = userTypeMapper.selectByPrimaryKey(user.getUserType());
        // 获取创建者
        Users founder = usersMapper.selectByPrimaryKey(user.getFounder());

        UserBO result = new UserBO();
        BeanUtils.copyProperties(user, result);
        result.setFounder(founder);
        result.setUserType(userType);
        result.setGender(Gender.codeOf(user.getGender()));

        // 获取JWT
        String jwt = JWTUtil.generateJWT(user.getId().toString(), result.getUserName());
        result.setJWT(jwt);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insertUser(UserAccountBO userAccountBO) throws Exception {
        Account account = new Account();
        Users users = new Users();
        BeanUtils.copyProperties(userAccountBO, account);
        BeanUtils.copyProperties(userAccountBO, users);
        account.setPassword(CommonUtil.getMD5Digest(account.getPassword()));
        accountMapper.insertReturnPK(account);
        System.out.println(account.getId());
        users.setAccountId((int)account.getId());
        users.setGender(userAccountBO.getGender());
        int count = usersMapper.insert(users);
        return count;
    }

    @Override
    public boolean queryUserTypeIsExist(Byte userTypeId) {
        Example userTypeExample = new Example(UserType.class);
        Example.Criteria userTypeCriteria = userTypeExample.createCriteria();
        userTypeCriteria.andEqualTo("id", userTypeId);
        UserType userType = userTypeMapper.selectOneByExample(userTypeExample);
        return userType != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int editUserProfile(Users users){
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public List<Users> selectAllUsers() {
        return usersMapper.selectAll();
    }
}
