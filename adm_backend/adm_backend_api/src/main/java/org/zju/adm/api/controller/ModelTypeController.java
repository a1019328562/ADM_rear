package org.zju.adm.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zju.adm.common.CommonResult;
import org.zju.adm.pojo.ModelType;
import org.zju.adm.service.ModelTypeService;

import java.util.List;

@Api(value = "模型操作", tags = {"模型相关接口"})
@RestController
@RequestMapping("/model")
public class ModelTypeController {

    @Autowired
    private ModelTypeService modelTypeService;

    @ApiOperation(value = "获取模型", notes = "获取模型", httpMethod = "GET")
    @RequestMapping(value = "/selectAllModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult selectAllUser(){
        List<ModelType> result = modelTypeService.selectAllModelType();
        return CommonResult.success(result);
    }
}
