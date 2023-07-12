package com.solvd.distancecalculator.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement(name = "roads")
public class Roads {
    @XmlElement(name = "road")
    private List<Road> roads;

    @XmlTransient
    public List<Road> getRoadList() {
        return roads;
    }

    public void setRoadList(List<Road> roadList) {
        this.roads = roadList;
    }
}
