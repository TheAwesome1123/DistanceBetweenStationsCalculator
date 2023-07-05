package com.solvd.distancecalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            int startingStationID = Integer.parseInt(args[0]);
            int endingStationID = Integer.parseInt(args[1]);
            LOGGER.info("The shortest distance between stations " + startingStationID + " and " + endingStationID
                + " is...");

            // Insert select here to get actual number of stations.
            int[][] distances = FloydAlgorithm.getMatrix(10);
            int[][] paths = FloydAlgorithm.getMatrix(10);
        }
        catch(NumberFormatException numberFormatException) {
            LOGGER.info(numberFormatException);
        }

    }
}
