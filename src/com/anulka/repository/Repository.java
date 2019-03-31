package com.anulka.repository;

import com.anulka.model.*;

import java.util.HashMap;
import java.util.Map;

public class Repository {

    private static Repository instance;

    public Map<String, StopsWithDate> stops = new HashMap<>();
    public Map<String, LinesWithDate> routes = new HashMap<>();
    public Map<String, TripsWithDate> trips = new HashMap<>();
    public Map<String, StopsInTripWithDate> stopsInTrip = new HashMap();
    public StopTimes3WithDate stopTimes = new StopTimes3WithDate();

    private Repository() {

    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }

        return instance;
    }
}
