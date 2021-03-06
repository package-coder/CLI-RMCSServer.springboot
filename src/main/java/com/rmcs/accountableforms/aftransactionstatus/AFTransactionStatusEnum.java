package com.rmcs.accountableforms.aftransactionstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum AFTransactionStatusEnum {
    PENDING("STATUS_PENDING", "PENDING"),
    CANCELLED("STATUS_CANCELLED", "CANCELLED"),
    COMPLETED("STATUS_COMPLETED", "COMPLETED");

    private final String id;
    private final String name;

    AFTransactionStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AFTransactionStatusEnum of(String id){

        //Find the id to the current enum values
        return Stream.of(AFTransactionStatusEnum.values())
                .filter(targetEnum -> targetEnum.id.equals(id.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
