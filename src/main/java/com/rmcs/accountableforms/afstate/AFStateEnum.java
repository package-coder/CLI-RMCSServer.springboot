package com.rmcs.accountableforms.afstate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum AFStateEnum {
    ISSUED("STATE_ISSUED", "ISSUED"),
    OPEN("STATE_OPEN", "OPEN");

    private final String id;
    private final String name;


    AFStateEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AFStateEnum of(String id){

        //Find the id to the current enum values
        return Stream.of(AFStateEnum.values())
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
