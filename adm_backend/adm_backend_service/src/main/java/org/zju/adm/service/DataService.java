package org.zju.adm.service;

import org.zju.adm.pojo.Data;
import org.zju.adm.pojo.DataType;

import java.util.List;

/**
 * ClassName: UserService
 * Description: TODO
 * Created by tiamo on 15/3/2020 11:56 上午
 */
public interface DataService {
    int insertData(Data data);
    int insertDataList(List<Data> dataList);
    boolean queryDataTypeIsExist(Byte dataTypeId);
    Byte queryDataTypeIdByName(String name);
    boolean queryDataTypeIsExistByName(String dataListName);
    int insertDataType(DataType dataType);
    List<Data> selectAllData();
    List<Data> selectAppointedData(Byte dataTypeId);
    List<DataType> selectAllDataType();

}
