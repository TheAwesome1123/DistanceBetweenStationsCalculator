package com.solvd.distancecalculator.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "station")
public class Station {
    @XmlElement(name = "stationID")
    private int id;
    @XmlElement(name = "stationName")
    private String name;

    @XmlTransient
    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @XmlTransient
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
