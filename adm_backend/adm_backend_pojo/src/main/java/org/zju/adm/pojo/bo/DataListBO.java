package org.zju.adm.pojo.bo;

import org.zju.adm.pojo.Data;

import java.util.List;

public class DataListBO {
    private String name;
    private List<Data> dataList;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
