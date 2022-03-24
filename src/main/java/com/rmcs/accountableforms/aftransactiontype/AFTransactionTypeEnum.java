package com.rmcs.accountableforms.aftransactiontype;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum AFTransactionTypeEnum {
    ISSUANCE("TYPE_ISSUANCE"),
    PURCHASE("TYPE_PURCHASE");

    private final String id;

    AFTransactionTypeEnum(String id) {
        this.id = id;
    }

    @JsonCreator
    public static AFTransactionTypeEnum of(String id){

        //Find the id to the current enum values
        return Stream.of(AFTransactionTypeEnum.values())
                .filter(targetEnum -> targetEnum.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getId() {
        return id;
    }
}
