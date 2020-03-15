package org.zju.adm.pojo.bo;

import org.zju.adm.common.enums.Gender;
import org.zju.adm.pojo.UserType;
import org.zju.adm.pojo.Users;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * ClassName: UserBO
 * Description: TODO
 * Created by tiamo on 15/3/2020 4:17 下午
 */
public class UserBO {
    private String jobNumber;
    private String userName;
    private Gender gender;
    private String telephone;
    private String avatar;
    private UserType userType;
    private Users founder;
    private String JWT;     // JsonWebToken，如设置@LoginPermission,则需在HTTP请求的headers添加User-Token:JWT

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Users getFounder() {
        return founder;
    }

    public void setFounder(Users founder) {
        this.founder = founder;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }
}
