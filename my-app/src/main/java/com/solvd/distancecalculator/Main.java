package com.solvd.distancecalculator;

import com.solvd.distancecalculator.daos.PathBetweenStationsDAO;
import com.solvd.distancecalculator.models.PathBetweenStations;
import com.solvd.distancecalculator.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            int startingStationID = Integer.parseInt(args[0]);
            int endingStationID = Integer.parseInt(args[1]);

            Integer[][] distances = FloydAlgorithm.getMatrix(10);
            Integer[][] stationsInShortestPath = FloydAlgorithm.getMatrix(10);

            List<PathBetweenStations> list = new PathBetweenStationsDAO().getPaths();
            LOGGER.info("Number of roads: " + list.size());

            // Adding and checking initial distances.
            FloydAlgorithm.addInitialDistances(distances, list);
            FloydAlgorithm.addInitialNodesInShortestDistance(distances, stationsInShortestPath);
            FloydAlgorithm.printDistances(distances);
            FloydAlgorithm.printDistances(stationsInShortestPath);

            // Insert Floyd's Algorithm implementation here.
            LOGGER.info("The shortest distance between stations " + startingStationID + " and " + endingStationID
                    + " is...");

            // Insert marshalling here.
            List<Station> finalStations =
                FloydAlgorithm.getStationsInShortestPath(stationsInShortestPath, 1, 2);

            LOGGER.info("hi");
        }
        catch(NumberFormatException numberFormatException) {
            LOGGER.info(numberFormatException);
        }
    }
}
