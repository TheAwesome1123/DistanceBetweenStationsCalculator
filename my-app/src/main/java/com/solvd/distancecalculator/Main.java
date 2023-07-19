package com.solvd.distancecalculator;

import com.solvd.distancecalculator.json.ShortestPathMarshallerJSON;
import com.solvd.distancecalculator.models.*;
import com.solvd.distancecalculator.xml.ShortestPathMarshallerXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static FloydHelper helper = new FloydHelper();

    public static void main(String[] args) {
        try {
            marshall(helper.calculateShortestDistance(Integer.parseInt(args[0]),Integer.parseInt(args[1])));

        }
        catch(NumberFormatException numberFormatException) {
            LOGGER.info(numberFormatException);
        }
    }

    public static void marshall(ShortestPath shortestPath) {
        ShortestPathMarshallerXML.marshallXML(shortestPath);
        ShortestPathMarshallerJSON.marshallJSON(shortestPath);
    }
}
