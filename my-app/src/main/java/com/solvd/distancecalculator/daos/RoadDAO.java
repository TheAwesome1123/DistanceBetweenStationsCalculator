package com.solvd.distancecalculator.daos;

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
}
