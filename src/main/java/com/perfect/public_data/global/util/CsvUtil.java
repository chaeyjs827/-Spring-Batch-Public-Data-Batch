package com.perfect.public_data.global.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CsvUtil {

    public static String[] getCsvHeaders(Resource resource) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String headerLine = reader.readLine();
            if (headerLine != null) {
                return headerLine.split(",");
            } else {
                throw new IllegalArgumentException("CSV file is empty or missing header");
            }
        }
    }

    public static Resource getGeneralRestaurantCsv() {
//        return new ClassPathResource("general-restaurant/all.csv");
//        return new ClassPathResource("general-restaurant/head_50000.csv");
        return new ClassPathResource("general-restaurant/head_5000.csv");
    }

}
