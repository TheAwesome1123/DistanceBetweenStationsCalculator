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
}
