package com.solvd.distancecalculator.daos;

import com.solvd.distancecalculator.models.PathBetweenStations;
import com.solvd.distancecalculator.services.SqlSessionFactoryGetter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class PathBetweenStationsDAO {
    private static SqlSessionFactory factory = SqlSessionFactoryGetter.getFactory();

    public List<PathBetweenStations> getPaths() {
        try(SqlSession session = factory.openSession()) {
            List<PathBetweenStations> paths = session.selectList("PathBetweenStationsMapper.xml.selectPaths");
            session.commit();

            return paths;
        }
    }
}
