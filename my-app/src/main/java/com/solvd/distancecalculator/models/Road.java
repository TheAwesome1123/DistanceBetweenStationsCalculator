package com.solvd.distancecalculator.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "road")
public class Road {
    @XmlElement(name = "roadID")
    private int id;
    @XmlElement(name = "roadName")
    private String name;
    @XmlElement(name = "length")
    private int distance;

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

    @XmlTransient
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}