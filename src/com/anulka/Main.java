package com.anulka;

import com.anulka.model.LinesWithDate;
import com.anulka.model.StopsWithDate;
import com.anulka.repository.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            Map<String, StopsWithDate> stops = loadStops();
            Map<String, LinesWithDate> routes = loadLines();
            System.out.println("Loaded " + stops.size() + " stops data");
            System.out.println("Loaded " + routes.size() + " lines data");
            Repository.getInstance().routes =routes;
            Repository.getInstance().stops = stops;
        } catch (IOException e) {
            System.out.println("Could not read stops data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Map<String, StopsWithDate> loadStops() throws IOException {
        File stopsFile = new File("stops.json");
        ObjectMapper mapper = getJsonObjectMapper();
        TypeReference mapType = new TypeReference<HashMap<String, StopsWithDate>>() {
        };
        return mapper.readValue(stopsFile, mapType);
    }
    public static Map<String, LinesWithDate> loadLines() throws IOException {
        File linesFile = new File("routes.json");
        ObjectMapper mapper = getJsonObjectMapper();
        TypeReference mapType = new TypeReference<HashMap<String, LinesWithDate>>() {
        };
        return mapper.readValue(linesFile, mapType);
    }

    public static ObjectMapper getJsonObjectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
