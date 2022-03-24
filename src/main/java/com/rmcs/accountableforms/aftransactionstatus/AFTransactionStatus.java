package com.rmcs.accountableforms.aftransactionstatus;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AFTransactionStatus {

    @Id
    private String id;
    private String name;

    public AFTransactionStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AFTransactionStatus(String id) {
        this.id = id;
    }

    public AFTransactionStatus() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AFTransactionStatus{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
