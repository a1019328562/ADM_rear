package org.zju.adm.pojo;

import javax.persistence.*;

public class Users {
    @Id
    private Integer id;

    @Column(name = "job_number")
    private String jobNumber;

    @Column(name = "user_name")
    private String userName;

    private Byte gender;

    private String telephone;

    private String avatar;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "user_type")
    private Byte userType;

    private Integer founder;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return job_number
     */
    public String getJobNumber() {
        return jobNumber;
    }

    /**
     * @param jobNumber
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return gender
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return account_id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * @return user_type
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * @return founder
     */
    public Integer getFounder() {
        return founder;
    }

    /**
     * @param founder
     */
    public void setFounder(Integer founder) {
        this.founder = founder;
    }
}