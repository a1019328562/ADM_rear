package org.zju.adm.common.enums;

/**
 * ClassName: YesOrNo
 * Description: TODO
 * Created by tiamo on 15/3/2020 10:50 上午
 */
public enum  YesOrNo {

    YES(1, "是"),
    NO(0, "否"),
    ;

    public final Integer type;

    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}