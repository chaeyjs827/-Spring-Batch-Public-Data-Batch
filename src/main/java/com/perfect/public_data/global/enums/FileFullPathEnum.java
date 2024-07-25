package com.perfect.public_data.global.enums;

import lombok.Getter;

@Getter
public enum FileFullPathEnum {

    GENERAL_RESTAURANT_FILE("general_restaurant", "general-restaurant/head_50000.csv");

    private final String name;
    private final String filePath;

    FileFullPathEnum(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }
}
