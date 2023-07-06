package com.solvd.distancecalculator.daos;

import com.solvd.distancecalculator.models.Station;
import com.solvd.distancecalculator.services.SqlSessionFactoryGetter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class StationDAO {
    private static SqlSessionFactory factory = SqlSessionFactoryGetter.getFactory();

    public Station getStation(int id) {
        try(SqlSession session = factory.openSession()) {
            Station station = session.selectOne("StationMapper.xml.selectStation", id);
            session.commit();

            return station;
        }
    }
}
