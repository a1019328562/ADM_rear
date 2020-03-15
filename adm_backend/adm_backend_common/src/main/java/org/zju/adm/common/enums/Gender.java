package org.zju.adm.common.enums;

/**
 * ClassName: YesOrNo
 * Description: TODO
 * Created by tiamo on 15/3/2020 10:50 上午
 */
public enum Gender {
    WOMAN(0, "女"),
    MAN(1, "男"),
    SECRET(2, "保密");

    public final Integer type;

    public final String value;

    Gender(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public static Gender codeOf(int type){
        for(Gender gender : values()){
            if(gender.getType() == type){
                return gender;
            }
        }
        return Gender.SECRET;
    }
}
