package com.rmcs.accountableforms.aftransactiontype;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum AFTransactionTypeEnum {
    ISSUANCE("TYPE_ISSUANCE", "ISSUANCE"),
    PURCHASE("TYPE_PURCHASE", "PURCHASE");

    private final String id;
    private final String name;

    AFTransactionTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AFTransactionTypeEnum of(String name){

        //Find the name to the current enum values
        return Stream.of(AFTransactionTypeEnum.values())
                .filter(targetEnum -> targetEnum.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public boolean equalByName(String name){
        return this.name.equals(name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
