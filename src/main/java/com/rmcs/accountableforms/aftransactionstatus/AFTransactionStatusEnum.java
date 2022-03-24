package com.rmcs.accountableforms.aftransactionstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum AFTransactionStatusEnum {
    PENDING("STATUS_PENDING"),
    CANCELLED("STATUS_CANCELLED"),
    COMPLETED("STATUS_COMPLETED");

    private final String id;

    AFTransactionStatusEnum(String id) {
        this.id = id;
    }

    @JsonCreator
    public static AFTransactionStatusEnum of(String id){

        //Find the id to the current enum values
        return Stream.of(AFTransactionStatusEnum.values())
                .filter(targetEnum -> targetEnum.id.equals(id.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getId() {
        return id;
    }
}
