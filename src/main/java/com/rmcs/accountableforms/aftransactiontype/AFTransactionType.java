package com.rmcs.accountableforms.aftransactiontype;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AFTransactionType {

    @Id
    private String id;
    private String name;

    public AFTransactionType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AFTransactionType(String id) {
        this.id = id;
    }

    public AFTransactionType() {}

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
        return "AFTransactionType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
