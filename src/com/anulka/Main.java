package com.anulka;

import com.anulka.model.LinesWithDate;
import com.anulka.model.StopsInTripWithDate;
import com.anulka.model.StopsWithDate;
import com.anulka.model.TripsWithDate;
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
            Map<String, TripsWithDate> trips = loadTrips();
            Map<String, StopsInTripWithDate> stopsInTrip = loadStopsInTrip();

            System.out.println("Loaded " + stops.size() + " stops data");
            System.out.println("Loaded " + routes.size() + " lines data");
            System.out.println("Loaded " + stopsInTrip.size() + " stopsInTrip data");
            System.out.println("Loaded " + trips.size() + " trips data");

            Repository.getInstance().routes =routes;
            Repository.getInstance().stops = stops;
            Repository.getInstance().trips = trips;
            Repository.getInstance().stopsInTrip = stopsInTrip;
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
    public static Map<String, TripsWithDate> loadTrips() throws IOException {
        File tripsFile = new File("trips.json");
        ObjectMapper mapper = getJsonObjectMapper();
        TypeReference mapType = new TypeReference<HashMap<String, TripsWithDate>>() {
        };
        return mapper.readValue(tripsFile, mapType);
    }
    public static Map<String, StopsInTripWithDate> loadStopsInTrip() throws IOException {
        File stopsInTripFile = new File("stopsintrips.json");
        ObjectMapper mapper = getJsonObjectMapper();
        TypeReference mapType = new TypeReference<HashMap<String, StopsInTripWithDate>>() {
        };
        return mapper.readValue(stopsInTripFile, mapType);
    }

    public static ObjectMapper getJsonObjectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
