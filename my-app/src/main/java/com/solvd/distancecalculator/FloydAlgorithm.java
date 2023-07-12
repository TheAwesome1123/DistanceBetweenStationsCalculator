package com.solvd.distancecalculator;

import com.solvd.distancecalculator.daos.RoadDAO;
import com.solvd.distancecalculator.daos.StationDAO;
import com.solvd.distancecalculator.models.PathBetweenStations;
import com.solvd.distancecalculator.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FloydAlgorithm {
    private static final Logger LOGGER = LogManager.getLogger(FloydAlgorithm.class);
    private static final Integer INFINITY = 9999;
    private static final StationDAO STATION_DAO = new StationDAO();
    private static final RoadDAO ROAD_DAO = new RoadDAO();

    public static Integer[][] getMatrix(int numOfStations) {
        // Each row and column corresponds to station ID.
        Integer[][] distances = new Integer[numOfStations][numOfStations];

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

    // Below 2 methods initialize the shortest distances and stations in the path.
    public static void addInitialDistances(Integer[][] matrix, List<PathBetweenStations> list) {
        for (PathBetweenStations pathBetweenStations : list) {
            int startStationID = pathBetweenStations.getStartingStationID();
            int endStationID = pathBetweenStations.getEndingStationID();
            int roadID = pathBetweenStations.getConnectingRoadID();
            int roadDistance = ROAD_DAO.getRoad(roadID).getDistance();

            // Roads are two-way.
            matrix[startStationID][endStationID] = roadDistance;
            matrix[endStationID][startStationID] = roadDistance;
        }
    }

    // Keeps track of stations that are in the shortest path; this method sets initial stations.
    public static void addInitialNodesInShortestDistance(Integer[][] distances, Integer[][] paths) {
        for(int i = 0; i < distances.length; i++) {
            for(int j = 0; j < distances[i].length; j++) {
                // If start and end station are the same, station in the shortest path is null.
                if(i == j) {
                    paths[i][j] = null;
                }
                // Null if no direct connection.
                else if(Objects.equals(distances[i][j], INFINITY)) {
                    paths[i][j] = null;
                    paths[j][i] = null;
                }
                else {
                    // Roads are two-way.
                    paths[i][j] = j;
                    paths[j][i] = i;
                }
            }
        }
    }

    public static void printDistances(Integer[][] matrix) {
        for (Integer[] ints : matrix) {
            String row = "";

            for (Integer anInt : ints) {
                if(anInt == null) {
                    row = row.concat("N ");
                }
                else {
                    row = row.concat(anInt + " ");

                }
            }

            LOGGER.info(row);
        }
    }

    public static List<Station> getStationsInShortestPath(Integer[][] stationsInShortestPaths,
        int startStationID, int endStationID) {
            List<Station> stationsInPath = new ArrayList<>();

            Station firstStation = STATION_DAO.getStation(startStationID);
            Station lastStation = STATION_DAO.getStation(endStationID);

            stationsInPath.add(firstStation);
            Station intermediateStation =
                getIntermediateStation(stationsInShortestPaths, startStationID, endStationID);

            while(intermediateStation != null) {
                stationsInPath.add(intermediateStation);
                intermediateStation = getIntermediateStation(stationsInShortestPaths,
                    intermediateStation.getID(), endStationID);
            }

            stationsInPath.add(lastStation);

            return stationsInPath;
    }

    public static Station getIntermediateStation(Integer[][] stationsInShortestPaths,
        int startStationID, int endStationID) {
            if(stationsInShortestPaths[startStationID][endStationID] == endStationID) {
                return null;
            }
            else {
                // Get intermediate stations until the end station in the shortest path is the given end one.
                int intermediateStationID = stationsInShortestPaths[startStationID][endStationID];
                return STATION_DAO.getStation(intermediateStationID);
            }
    }

    public static void runAlgorithm(Integer[][] distance, Integer[][] shortestPaths) {
        for (int k = 0; k < distance.length; k++) {
            // Pick all vertices as source one by one
            for (int i = 0; i < distance.length; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (int j = 0; j < distance.length; j++) {
                    // If vertex k is on the shortest path
                    // from i to j, then update the value of
                    // distance[i][j]
                    if (distance[i][j] > distance[i][k] + distance[k][j]){
                        distance[i][j] = distance[i][k] + distance[k][j];
                        shortestPaths[i][j] = shortestPaths[i][k];
                    }
                }
            }
        }
    }
}
