package com.solvd.distancecalculator;

import com.solvd.distancecalculator.daos.PathBetweenStationsDAO;
import com.solvd.distancecalculator.daos.RoadDAO;
import com.solvd.distancecalculator.daos.StationDAO;
import com.solvd.distancecalculator.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FloydHelper {
    private static final Logger LOGGER = LogManager.getLogger(FloydHelper.class);
    private static final PathBetweenStationsDAO PATH_BETWEEN_STATIONS_DAO = new PathBetweenStationsDAO();
    private static final StationDAO STATION_DAO = new StationDAO();
    private static final RoadDAO ROAD_DAO = new RoadDAO();

    public ShortestPath calculateShortestDistance(int startingStationID, int endingStationID) {
        LOGGER.info("Calculating shortest path.");
        ShortestPath shortestPath = new ShortestPath();
        Stations stations = new Stations();
        Roads roads = new Roads();
        List<Station> stationList = new ArrayList<>();
        int distance = 0;

        if(startingStationID == endingStationID) {
            Station station = STATION_DAO.getStation(startingStationID);
            stationList.add(station);
            stationList.add(station);
            stations.setStationList(stationList);
            LOGGER.info("Start and endpoints are the same. No travel distance.");

        } else {
            LOGGER.info("Retrieving paths.");
            int numOfPaths = PATH_BETWEEN_STATIONS_DAO.getNumOfPathsInDB();
            List<PathBetweenStations> list = PATH_BETWEEN_STATIONS_DAO.getPaths();

            // Insert select here to get actual number of stations.
            Integer[][] distances = FloydAlgorithm.getMatrix(numOfPaths);
            Integer[][] stationsInShortestPath = FloydAlgorithm.getMatrix(numOfPaths);

            FloydAlgorithm.addInitialDistances(distances, list);
            FloydAlgorithm.addInitialNodesInShortestDistance(distances, stationsInShortestPath);
            LOGGER.info("Running algorithm.");
            // Floyd's Algorithm.
            FloydAlgorithm.runAlgorithm(distances, stationsInShortestPath);

            stations.setStationList(FloydAlgorithm.
                    getStationsInShortestPath(stationsInShortestPath, startingStationID, endingStationID));
            shortestPath.setStations(stations);
            distance = distances[startingStationID][endingStationID];

            stationList = stations.getStationList();
            roads.setRoadList(generateRoadList(stationList));
        }

        return buildShortestPath(stations,roads,distance);
    }

    private List<Road> generateRoadList(List<Station> list) {
        List<Road> roadList = new ArrayList<>();

        for(int i = 0; i < list.size() - 1; i++) {
            // Make PathBetweenStations using current stations, and use its start/end IDs to select Road.
            Station start = list.get(i);
            Station end = list.get(i + 1);
            PathBetweenStations path = new PathBetweenStations();

            path.setStartingStationID(start.getID());
            path.setEndingStationID(end.getID());
            Road road = ROAD_DAO.getRoadGivenStartAndEnd(path);

            roadList.add(road);
        }
        return roadList;
    }

    private ShortestPath buildShortestPath(Stations stations, Roads roads, int distance) {
        ShortestPath shortestPath = new ShortestPath();
        shortestPath.setStations(stations);
        shortestPath.setRoads(roads);
        shortestPath.setDistance(distance);
        LOGGER.info("Constructing shortest path.");

        return shortestPath;
    }
}
