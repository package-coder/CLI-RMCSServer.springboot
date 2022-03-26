package com.rmcs.accountableforms.afprefix;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rmcs.accountableforms.afstate.AFStateEnum;
import com.rmcs.accountableforms.aftransactiontype.AFTransactionTypeEnum;

import java.util.stream.Stream;

public enum AFPrefixEnum {
    REQUEST("PREFIX_REQUEST", "RIS"),
    TRANSACTION("PREFIX_TRANSACTION", "IRAF");

    private final String id;
    private final String name;


    AFPrefixEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AFPrefixEnum of(String id){

        //Find the id to the current enum values
        return Stream.of(AFPrefixEnum.values())
                .filter(targetEnum -> targetEnum.id.equals(id))
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
