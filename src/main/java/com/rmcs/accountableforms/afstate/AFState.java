package com.rmcs.accountableforms.afstate;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AFState {
    @Id
    private String id;
    private String name;

    public AFState(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AFState(String id) {
        this.id = id;
    }

    public AFState() {
    }

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
        return "AFState{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
