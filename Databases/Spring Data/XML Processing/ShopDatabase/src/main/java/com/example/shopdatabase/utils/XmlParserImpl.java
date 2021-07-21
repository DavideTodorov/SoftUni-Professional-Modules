package com.example.shopdatabase.utils;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class XmlParserImpl implements XmlParser {

    private JAXBContext jaxbContext;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException {
        jaxbContext = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


        return (T) unmarshaller.unmarshal(new FileReader(filePath));
    }

    @Override
    public <T> void writeToFail(String filePath, T entity) throws JAXBException, IOException {
        jaxbContext = JAXBContext.newInstance(entity.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(entity, new File(filePath));
    }
}
