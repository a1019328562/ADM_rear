package org.zju.adm.common.component.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: VlidationResult
 * Description: TODO
 * Created by tiamo on 16/12/2019 9:45 上午
 */
public class ValidationResult {
    // 校验结果是否有错
    private boolean hasErrors = false;

    // 存放错误信息的map
    private Map<String, String> errorMsgMap = new HashMap<>();

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    // 实现通用的格式化字符串信息获取错误结果的msg方法
    public String getErrMsg(){
        return StringUtils.join(this.errorMsgMap.values().toArray(), ",");
    }
}
