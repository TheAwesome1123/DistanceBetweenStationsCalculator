package com.solvd.distancecalculator.xml;

import com.solvd.distancecalculator.models.ShortestPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class ShortestPathMarshallerXML {
    private static final Logger LOGGER = LogManager.getLogger(ShortestPathMarshallerXML.class);

    public static void marshallXML(ShortestPath path) {
        try {
            JAXBContext context = JAXBContext.newInstance(ShortestPath.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(path, new File("./Route.xml"));
        }
        catch (JAXBException e) {
            LOGGER.info(e);
        }
    }
}
