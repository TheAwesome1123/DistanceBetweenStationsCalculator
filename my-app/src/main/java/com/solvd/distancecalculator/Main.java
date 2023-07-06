package com.solvd.distancecalculator;

import com.solvd.distancecalculator.daos.PathBetweenStationsDAO;
import com.solvd.distancecalculator.models.PathBetweenStations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            int startingStationID = Integer.parseInt(args[0]);
            int endingStationID = Integer.parseInt(args[1]);
            LOGGER.info("The shortest distance between stations " + startingStationID + " and " + endingStationID
                + " is...");

            int[][] distances = FloydAlgorithm.getMatrix(10);

            // Each entry represents station in shortest route from start (row) to end (col).
            int[][] shortestPaths = FloydAlgorithm.getMatrix(10);

            List<PathBetweenStations> list = new PathBetweenStationsDAO().getPaths();
            LOGGER.info("Number of roads: " + list.size());
        }
        catch(NumberFormatException numberFormatException) {
            LOGGER.info(numberFormatException);
        }
    }
}
