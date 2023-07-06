package com.solvd.distancecalculator.models;

public class PathBetweenStations {
    private int pathID;
    private int startingStationID;
    private int endingStationID;
    private int connectingRoadID;

    public int getPathID() {
        return pathID;
    }

    public void setPathID(int pathID) {
        this.pathID = pathID;
    }

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

    public int getConnectingRoadID() {
        return connectingRoadID;
    }

    public void setConnectingRoadID(int connectingRoadID) {
        this.connectingRoadID = connectingRoadID;
    }
}