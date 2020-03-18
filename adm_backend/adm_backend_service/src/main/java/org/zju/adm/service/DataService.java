package org.zju.adm.service;

import org.zju.adm.pojo.Data;

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
    boolean queryDataLabelTypeIsExist(Byte dataLabelTypeId);
    List<Data> selectAllData();
}
