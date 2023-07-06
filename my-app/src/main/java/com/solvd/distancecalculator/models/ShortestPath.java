package com.solvd.distancecalculator.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "path")
public class ShortestPath {
    private Station start;
    private Station end;
    private List<Station> intermediateStations;
    private List<Road> roads;

    @XmlElement(name = "start")
    public Station getStart() {
        return start;
    }

    public void setStart(Station start) {
        this.start = start;
    }

    @XmlElement(name = "end")
    public Station getEnd() {
        return end;
    }

    public void setEnd(Station end) {
        this.end = end;
    }

    public List<Station> getIntermediateStations() {
        return intermediateStations;
    }

    public void setIntermediateStations(List<Station> intermediateStations) {
        this.intermediateStations = intermediateStations;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }
}
