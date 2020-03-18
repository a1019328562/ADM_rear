package org.zju.adm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

public class Data {
    @Id
    private Integer id;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    private Double value;

    @Column(name = "data_type_id")
    private Byte dataTypeId;

    @Column(name = "data_marked_label_id")
    private Byte dataMarkedLabelId;

    @Column(name = "data_predicted_label_id")
    private Byte dataPredictedLabelId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * @return data_type_id
     */
    public Byte getDataTypeId() {
        return dataTypeId;
    }

    /**
     * @param dataTypeId
     */
    public void setDataTypeId(Byte dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    /**
     * @return data_marked_label_id
     */
    public Byte getDataMarkedLabelId() {
        return dataMarkedLabelId;
    }

    /**
     * @param dataMarkedLabelId
     */
    public void setDataMarkedLabelId(Byte dataMarkedLabelId) {
        this.dataMarkedLabelId = dataMarkedLabelId;
    }

    /**
     * @return data_predicted_label_id
     */
    public Byte getDataPredictedLabelId() {
        return dataPredictedLabelId;
    }

    /**
     * @param dataPredictedLabelId
     */
    public void setDataPredictedLabelId(Byte dataPredictedLabelId) {
        this.dataPredictedLabelId = dataPredictedLabelId;
    }
}