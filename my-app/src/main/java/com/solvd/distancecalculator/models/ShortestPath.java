package com.solvd.distancecalculator.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement(name = "path")
public class ShortestPath {
    @XmlElement(name = "distance")
    private int distance;
    @XmlElement(name = "stations")
    private Stations stations;
    @XmlElement(name = "roads")
    private Roads roads;

    @XmlTransient
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @XmlTransient
    public Stations getStations() {
        return stations;
    }

    public void setStations(Stations stations) {
        this.stations = stations;
    }

    @XmlTransient
    public Roads getRoads() {
        return roads;
    }

    public void setRoads(Roads roads) {
        this.roads = roads;
    }
}
