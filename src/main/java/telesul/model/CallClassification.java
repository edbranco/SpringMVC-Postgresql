/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import telesul.config.CustomDateTimeHMDeserializer;
import telesul.config.CustomDateTimeHMSerializer;

/**
 *
 * @author lmohan
 */
@Entity
public class CallClassification implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String loginID;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomDateTimeHMSerializer.class)
    @JsonDeserialize(using = CustomDateTimeHMDeserializer.class)
    private Date receivedDate;//current timestamp
    private String classification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    @Override
    public String toString() {
        return "CallClassification{" + "id=" + id + ", loginID=" + loginID + ", receivedDate=" + receivedDate + ", classification=" + classification + '}';
    }

}
