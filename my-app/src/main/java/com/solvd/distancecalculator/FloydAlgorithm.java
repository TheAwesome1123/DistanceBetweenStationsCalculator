package com.solvd.distancecalculator;

import com.solvd.distancecalculator.daos.RoadDAO;
import com.solvd.distancecalculator.models.PathBetweenStations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FloydAlgorithm {
    private static final Logger LOGGER = LogManager.getLogger(FloydAlgorithm.class);
    private static final Integer INFINITY = Integer.MAX_VALUE;

    public static int[][] getMatrix(int numOfStations) {
        // Each row and column corresponds to station ID.
        int[][] distances = new int[numOfStations][numOfStations];

        for(int i = 0; i < distances.length; i++) {
            for(int j = 0; j < distances[i].length; j++) {
                // Distance from station to itself is 0; non-existent one is INFINITY.
                if(i == j) {
                    distances[i][j] = 0;
                }
                else {
                    distances[i][j] = INFINITY;
                }
            }
        }

        return distances;
    }

    // Below 2 methods are meant to be used to check that initial distances are pulled correctly.
    public static void addInitialDistances(int[][] matrix, List<PathBetweenStations> list) {
        for (PathBetweenStations pathBetweenStations : list) {
            int startStationID = pathBetweenStations.getStartingStationID();
            int endStationID = pathBetweenStations.getEndingStationID();
            int roadID = pathBetweenStations.getConnectingRoadID();
            int roadDistance = new RoadDAO().getRoad(roadID).getDistance();

            // Matrix is 0-indexed, but database entries are not.
            matrix[startStationID - 1][endStationID - 1] = roadDistance;
        }
    }

    public static void printDistances(int[][] matrix) {
        for (int[] ints : matrix) {
            String row = "";

            for (int anInt : ints) {
                row = row.concat(anInt + " ");
            }

            LOGGER.info(row);
        }
    }
}
