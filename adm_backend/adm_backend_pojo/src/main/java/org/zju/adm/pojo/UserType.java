package org.zju.adm.pojo;

import javax.persistence.*;

@Table(name = "user_type")
public class UserType {
    @Id
    private Integer id;

    @Column(name = "type_name")
    private String typeName;

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
     * @return type_name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}