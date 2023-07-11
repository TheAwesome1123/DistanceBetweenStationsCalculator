package com.solvd.distancecalculator;

import com.solvd.distancecalculator.daos.PathBetweenStationsDAO;
import com.solvd.distancecalculator.daos.RoadDAO;
import com.solvd.distancecalculator.models.*;
import com.solvd.distancecalculator.xml.ShortestPathMarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final PathBetweenStationsDAO PATH_BETWEEN_STATIONS_DAO = new PathBetweenStationsDAO();
    private static final RoadDAO ROAD_DAO = new RoadDAO();

    public static void main(String[] args) {
        try {
            int startingStationID = Integer.parseInt(args[0]);
            int endingStationID = Integer.parseInt(args[1]);
            int numOfPaths = PATH_BETWEEN_STATIONS_DAO.getNumOfPathsInDB();

            Integer[][] distances = FloydAlgorithm.getMatrix(numOfPaths);
            Integer[][] stationsInShortestPath = FloydAlgorithm.getMatrix(numOfPaths);

            List<PathBetweenStations> list = PATH_BETWEEN_STATIONS_DAO.getPaths();
            LOGGER.info("Number of roads: " + numOfPaths);

            LOGGER.info("Calculating shortest distance between stations " + startingStationID + " and " +
                endingStationID);

            // Adding and checking initial distances.
            FloydAlgorithm.addInitialDistances(distances, list);
            FloydAlgorithm.addInitialNodesInShortestDistance(distances, stationsInShortestPath);


            // Insert Floyd's Algorithm implementation here.


            // Setting up ShortestPath for marshalling.
            Stations stations = new Stations();
            stations.setStationList(FloydAlgorithm.
                getStationsInShortestPath(stationsInShortestPath, startingStationID, endingStationID));

            ShortestPath shortestPath = new ShortestPath();
            shortestPath.setStations(stations);
            shortestPath.setDistance(distances[startingStationID][endingStationID]);

            // To get the roads, get roads where the connected stations are two next to each other in list.
            Roads roads = new Roads();
            List<Road> roadList = new ArrayList<>();
            List<Station> stationList = stations.getStationList();

            for(int i = 0; i < stationList.size() - 1; i++) {
                // Make PathBetweenStations using current stations, and use its start/end IDs to select Road.
                Station start = stationList.get(i);
                Station end = stationList.get(i + 1);
                PathBetweenStations path = new PathBetweenStations();

                path.setStartingStationID(start.getID());
                path.setEndingStationID(end.getID());
                Road road = ROAD_DAO.getRoadGivenStartAndEnd(path);
                roadList.add(road);
            }

            roads.setRoadList(roadList);
            shortestPath.setRoads(roads);

            ShortestPathMarshaller.marshallXML(shortestPath);
        }
        catch(NumberFormatException numberFormatException) {
            LOGGER.info(numberFormatException);
        }
    }
}
