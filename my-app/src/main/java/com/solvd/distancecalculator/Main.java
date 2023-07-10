package com.solvd.distancecalculator;

import com.solvd.distancecalculator.daos.PathBetweenStationsDAO;
import com.solvd.distancecalculator.models.PathBetweenStations;
import com.solvd.distancecalculator.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final PathBetweenStationsDAO PATH_BETWEEN_STATIONS_DAO = new PathBetweenStationsDAO();

    public static void main(String[] args) {
        try {
            int startingStationID = Integer.parseInt(args[0]);
            int endingStationID = Integer.parseInt(args[1]);
            int numOfPaths = PATH_BETWEEN_STATIONS_DAO.getNumOfPathsInDB();

            Integer[][] distances = FloydAlgorithm.getMatrix(numOfPaths);
            Integer[][] stationsInShortestPath = FloydAlgorithm.getMatrix(numOfPaths);

            List<PathBetweenStations> list = new PathBetweenStationsDAO().getPaths();
            LOGGER.info("Number of roads: " + numOfPaths);

            LOGGER.info("Calculating shortest distance between stations " + startingStationID + " and " +
                endingStationID);

            // Adding and checking initial distances.
            FloydAlgorithm.addInitialDistances(distances, list);
            FloydAlgorithm.addInitialNodesInShortestDistance(distances, stationsInShortestPath);
            FloydAlgorithm.printDistances(distances);
            FloydAlgorithm.printDistances(stationsInShortestPath);

            // Insert Floyd's Algorithm implementation here.


            // Insert marshalling here.
            List<Station> finalStations =
                FloydAlgorithm.getStationsInShortestPath(stationsInShortestPath, startingStationID, endingStationID);

            for(Station station : finalStations) {
                LOGGER.info(station.getID() + " " + station.getName());
            }
        }
        catch(NumberFormatException numberFormatException) {
            LOGGER.info(numberFormatException);
        }
    }
}
