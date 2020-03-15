package org.zju.adm.common;

/**
 * ClassName: CommonResult
 * Description: 通用的请求返回结果
 * Created by tiamo on 15/3/2020 11:05 上午
 */
public class CommonResult {

    // 表明请求的返回结果，"success"或"failure"
    private String status;

    private Object data;

    // 定义一个通用的返回对象的方法
    public static CommonResult success(){
        return CommonResult.create(null, "success");
    }

    public static CommonResult success(Object result){
        return CommonResult.create(result, "success");
    }

    public static CommonResult failure(Object result) {
        return CommonResult.create(result, "failure");
    }

    public static CommonResult create(Object result, String status){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(status);
        commonResult.setData(result);
        return commonResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess(){
        if(this.status.equals("success")){
            return true;
        }
        return false;
    }

}
