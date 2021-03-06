package org.zju.adm.pojo;

import javax.persistence.*;

public class Account {
    @Id
    private Byte id;

    @Column(name = "account_name")
    private String accountName;

    private String password;

    /**
     * @return id
     */
    public Byte getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Byte id) {
        this.id = id;
    }

    /**
     * @return account_name
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}