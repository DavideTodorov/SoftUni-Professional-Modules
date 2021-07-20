package com.example.cardealer.services;

import com.example.cardealer.entities.Part;

import java.io.IOException;
import java.util.Set;

public interface PartService {

    void seedData() throws IOException;

    Set<Part> getRandomParts();
}
