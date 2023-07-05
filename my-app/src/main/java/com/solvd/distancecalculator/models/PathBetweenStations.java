package com.solvd.distancecalculator.models;

import java.util.List;

public class PathBetweenStations {
    private int startingStationID;
    private int endingStationID;
    private List<Road> roads;
    private List<Station> otherStations;

    public int getStartingStationID() {
        return this.startingStationID;
    }

    public void setStartingStationID(int startingStationID) {
        this.startingStationID = startingStationID;
    }

    public int getEndingStationID() {
        return this.endingStationID;
    }

    public void setEndingStationID(int endingStationID) {
        this.endingStationID = endingStationID;
    }

    public List<Road> getRoads() {
        return this.roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public List<Station> getOtherStations() {
        return this.otherStations;
    }

    public void setOtherStations(List<Station> otherStations) {
        this.otherStations = otherStations;
    }
}