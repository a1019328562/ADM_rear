package org.zju.adm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.zju.adm.mapper.ModelTypeMapper;
import org.zju.adm.pojo.ModelType;
import org.zju.adm.service.ModelTypeService;

import java.util.List;


@Service
public class ModelTypeServiceImpl implements ModelTypeService {
    @Autowired
    private ModelTypeMapper modelTypeMapper;

    @Override
    public List<ModelType> selectAllModelType() {
        return modelTypeMapper.selectAll();
    }
}
