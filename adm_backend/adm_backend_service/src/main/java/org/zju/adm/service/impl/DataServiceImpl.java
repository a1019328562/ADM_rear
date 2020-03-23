package org.zju.adm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zju.adm.mapper.*;
import org.zju.adm.pojo.Data;
import org.zju.adm.pojo.DataLabelType;
import org.zju.adm.pojo.DataType;
import org.zju.adm.service.DataService;

import java.util.List;


@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private DataLabelTypeMapper dataLabelTypeMapper;

    @Autowired
    private DataTypeMapper dataTypeMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insertData(Data data){
        int result = dataMapper.insert(data);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean queryDataTypeIsExist(Byte dataTypeId) {
        DataType dataType = dataTypeMapper.selectByPrimaryKey(dataTypeId);
        return dataType != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean queryDataLabelTypeIsExist(Byte dataLabelTypeId) {
        DataLabelType dataLabelType = dataLabelTypeMapper.selectByPrimaryKey(dataLabelTypeId);
        return dataLabelType != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Data> selectAllData() {
        return dataMapper.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insertDataList(List dataList) {
        int result =  dataMapper.insertList(dataList);
        return result;
    }
}
