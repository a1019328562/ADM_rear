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
import tk.mybatis.mapper.entity.Example;

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
    public Byte queryDataTypeIdByName(String name) {
        Example dataTypeExample = new Example(DataType.class);
        Example.Criteria dataTypeCriteria = dataTypeExample.createCriteria();
        dataTypeCriteria.andEqualTo("name", name);
        DataType dataType=  dataTypeMapper.selectOneByExample(dataTypeExample);
        return dataType.getId();
    }

    @Override
    public boolean queryDataTypeIsExistByName(String name) {
        Example dataTypeExample = new Example(DataType.class);
        Example.Criteria dataTypeCriteria = dataTypeExample.createCriteria();
        dataTypeCriteria.andEqualTo("name", name);
        DataType dataType=  dataTypeMapper.selectOneByExample(dataTypeExample);
        return dataType!=null;
    }

    @Override
    public int insertDataType(DataType dataType) {
        return dataTypeMapper.insert(dataType);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Data> selectAllData() {
        return dataMapper.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Data> selectAppointedData(Byte dataTypeId) {
        Example dataExample = new Example(Data.class);
        Example.Criteria dataCriteria = dataExample.createCriteria();
        dataCriteria.andEqualTo("dataTypeId", dataTypeId);
        return dataMapper.selectByExample(dataExample);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<DataType> selectAllDataType() {
        return dataTypeMapper.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insertDataList(List dataList) {
        int result =  dataMapper.insertList(dataList);
        return result;
    }
}
