package com.rmcs.accountableforms.afstate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum AFStateEnum {
    ISSUED("STATE_ISSUED"),
    OPEN("STATE_OPEN");

    private final String id;

    AFStateEnum(String id) {
        this.id = id;
    }

    @JsonCreator
    public static AFStateEnum of(String id){

        //Find the id to the current enum values
        return Stream.of(AFStateEnum.values())
                .filter(targetEnum -> targetEnum.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getId() {
        return id;
    }
}
