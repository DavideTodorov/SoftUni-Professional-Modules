package com.example.football.service.impl;

import com.example.football.models.dto.StatInputRootDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {
    private static final String STATS_FILE_PATH = "src/main/resources/files/xml/stats.xml";

    private final StatRepository statRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public StatServiceImpl(StatRepository statRepository, XmlParser xmlParser,
                           ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.statRepository = statRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files
                .readString(Path.of(STATS_FILE_PATH));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {
        StatInputRootDto statInputRootDto = xmlParser.fromFile(STATS_FILE_PATH, StatInputRootDto.class);

        StringBuilder sb = new StringBuilder();

        List<Stat> stats = statInputRootDto
                .getStats()
                .stream()
                .filter(statInputDto -> {
                    boolean valid = validationUtil.isValid(statInputDto);

                    if (valid) {
                        sb.append(String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                                statInputDto.getShooting(), statInputDto.getPassing(), statInputDto.getEndurance()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid Stat")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(statInputDto -> modelMapper.map(statInputDto, Stat.class))
                .collect(Collectors.toList());

        statRepository.saveAll(stats);

        return sb.toString().trim();
    }

    @Override
    public Stat findById(Integer id) {
        return statRepository.findById(id).orElse(null);
    }
}
