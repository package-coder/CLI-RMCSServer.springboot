package com.rmcs.accountableforms.afprefix;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AFPrefix {
    @Id
    private String id;
    private String name;

    public AFPrefix(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AFPrefix() {}

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
        return "AFPrefix{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
