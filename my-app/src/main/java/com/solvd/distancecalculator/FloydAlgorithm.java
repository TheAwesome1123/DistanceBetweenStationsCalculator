package com.solvd.distancecalculator;

public class FloydAlgorithm {
    private static final Integer INFINITY = Integer.MAX_VALUE;

    public static int[][] getMatrix(int numOfStations) {
        int[][] distances = new int[numOfStations][numOfStations];

        for(int i = 0; i < distances.length; i++) {
            for(int j = 0; j < distances[i].length; j++) {
                // Distance from station to itself is 0.
                if(i == j) {
                    distances[i][j] = 0;
                }
            }
        }

        return distances;
    }

    public static Integer[][] runAlgorithm(Integer[][] distance,int vertices) {
        for (int k = 0; k < vertices; k++) {
            // Pick all vertices as source one by one
            for (int i = 0; i < vertices; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (int j = 0; j < vertices; j++) {
                    // If vertex k is on the shortest path
                    // from i to j, then update the value of
                    // distance[i][j]
                    if (distance[i][j] > distance[i][k] + distance[k][j]){
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        return distance;
    }

}
