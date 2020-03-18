package org.zju.adm.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zju.adm.common.CommonResult;
import org.zju.adm.common.exception.CommonError;
import org.zju.adm.pojo.Data;
import org.zju.adm.service.DataService;

import java.util.List;

/**
 * ClassName: UserController
 * Description: 用户服务相关接口
 * Created by tiamo on 15/3/2020 10:58 上午
 */
@Api(value = "数据操作", tags = {"数据相关接口"})
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @ApiOperation(value = "插入单个数据", notes = "插入单个数据", httpMethod = "POST")
    @RequestMapping(value = "/insertData", method = RequestMethod.POST)
    public CommonResult insertData(@RequestBody Data data){
        if(!canInsert(data)){
            return CommonResult.failure(CommonError.NO_SACH_TYPE_ERROR);
        }
        int result = dataService.insertData(data);
        if (result<=0){
            return CommonResult.failure(CommonError.OPERATION_ERROR);
        }
        return CommonResult.success();
    }

    @ApiOperation(value = "插入数据集", notes = "插入数据集", httpMethod = "POST")
    @RequestMapping(value = "/insertDataList", method = RequestMethod.POST, produces =  MediaType.APPLICATION_JSON_VALUE)
    public CommonResult insertDataList(@RequestBody List<Data> datalist){
        for (Data data:datalist) {
            if(!canInsert(data)){
                return CommonResult.failure(CommonError.NO_SACH_TYPE_ERROR);
            }
        }
        int result = dataService.insertDataList(datalist);
        if (result<=0){
            return CommonResult.failure(CommonError.OPERATION_ERROR);
        }
        return CommonResult.success();
    }

    @ApiOperation(value = "查询所有数据", notes = "查询所有数据", httpMethod = "GET")
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public CommonResult insertData(){
        List<Data> result = dataService.selectAllData();
        return CommonResult.success(result);
    }

    private boolean canInsert(Data data){
        Byte dataTypeId = data.getDataTypeId();
        Byte dataMarkedLabelId = data.getDataMarkedLabelId();
        boolean dataTypeIsExist = dataService.queryDataTypeIsExist(dataTypeId);
        boolean dataMarkedLabelIsExist = dataService.queryDataLabelTypeIsExist(dataMarkedLabelId);
        return dataTypeIsExist&&dataMarkedLabelIsExist;
    }

}
