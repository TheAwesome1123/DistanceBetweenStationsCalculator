package com.solvd.distancecalculator.daos;

import com.solvd.distancecalculator.models.PathBetweenStations;
import com.solvd.distancecalculator.models.Road;
import com.solvd.distancecalculator.services.SqlSessionFactoryGetter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class RoadDAO {
    private static SqlSessionFactory factory = SqlSessionFactoryGetter.getFactory();

    public Road getRoad(int id) {
        try(SqlSession session = factory.openSession()) {
            Road road = session.selectOne("RoadMapper.xml.selectRoad", id);
            session.commit();

            return road;
        }
    }

    public Road getRoadGivenStartAndEnd(PathBetweenStations path) {
        try(SqlSession session = factory.openSession()) {
            int startID = path.getStartingStationID();
            int endID = path.getEndingStationID();

            Integer roadID = session.selectOne("RoadMapper.xml.selectRoadGivenStartAndEnd", path);

            // A road can connect two stations, but the start/end order matters due to how the database is designed.
            // If two stations connect, but the order is in reverse of what the database has, switch the start and end stations.
            if(roadID == null) {
                path.setStartingStationID(endID);
                path.setEndingStationID(startID);
                roadID = session.selectOne("RoadMapper.xml.selectRoadGivenStartAndEnd", path);
            }
            session.commit();

            return getRoad(roadID);
        }
    }
}
