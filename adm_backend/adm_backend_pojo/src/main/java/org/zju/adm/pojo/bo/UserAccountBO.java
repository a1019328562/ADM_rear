package org.zju.adm.pojo.bo;

import org.zju.adm.common.enums.Gender;

public class UserAccountBO {
    //UserType中的属性
    private Byte userType;
    //account的属性
    private String accountName;
    private String password;
    //User中的属性
    private String jobNumber;
    private String userName;
    private Byte gender;
    private String telephone;
    private String avatar;
    private Integer founder;

    public UserAccountBO() {
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

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

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
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

    public Integer getFounder() {
        return founder;
    }

    public void setFounder(Integer founder) {
        this.founder = founder;
    }
}
