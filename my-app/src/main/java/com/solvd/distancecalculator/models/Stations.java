package com.solvd.distancecalculator.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement(name = "stations")
public class Stations {
    @XmlElement(name = "station")
    private List<Station> stations;

    @XmlTransient
    public List<Station> getStationList() {
        return stations;
    }

    public void setStationList(List<Station> stationList) {
        this.stations = stationList;
    }
}
