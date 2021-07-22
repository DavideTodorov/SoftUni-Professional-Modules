package com.example.cardealer.services;

import com.example.cardealer.entities.Part;
import com.example.cardealer.entities.dtos.inputDtos.PartInputDto;

import java.util.List;
import java.util.Set;

public interface PartService {

    void seedData(List<PartInputDto> parts);

    Set<Part> getRandomParts();
}
