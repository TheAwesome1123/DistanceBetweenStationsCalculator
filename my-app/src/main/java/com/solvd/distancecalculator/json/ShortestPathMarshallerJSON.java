package com.solvd.distancecalculator.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.distancecalculator.models.ShortestPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ShortestPathMarshallerJSON {
    private static final Logger LOGGER = LogManager.getLogger(ShortestPathMarshallerJSON.class);

    public static void marshallJSON(ShortestPath shortestPath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(
            "./RouteJSON.json"), shortestPath);
        }
        catch (IOException e) {
            LOGGER.info(e);
        }
    }
}
