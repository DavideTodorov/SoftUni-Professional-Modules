package com.example.shopdatabase.utils;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface XmlParser {

    <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException;

    <T> void writeToFail(String filePath, T entity) throws JAXBException, IOException;
}
