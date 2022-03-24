package com.rmcs.accountableforms.aftype;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class AFType {

    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private String title;

    @Min(value = 1)
    private int seriesLength;

    private String unit = "STUB";

    private int quantity;

    public AFType() {}

    public AFType(String id,
                  String title,
                  int seriesLength,
                  String unit,
                  int quantity) {

        this.id = id;
        this.title = title;
        this.seriesLength = seriesLength;
        this.unit = unit;
        this.quantity = quantity;
    }

    public AFType(String id){
        this.id = id;
    }


    public int getSeriesLength() {
        return seriesLength;
    }

    public void setSeriesLength(int seriesLength) {
        this.seriesLength = seriesLength;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AFType{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", seriesLength=" + seriesLength +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

