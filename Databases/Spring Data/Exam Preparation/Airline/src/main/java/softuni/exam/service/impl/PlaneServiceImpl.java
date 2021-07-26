package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.PlanesInputRootDto;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaneServiceImpl implements PlaneService {
    private static final String PLANES_FILE_PATH = "src/main/resources/files/xml/planes.xml";

    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper,
                            ValidationUtil validationUtil, XmlParser xmlParser) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files
                .readString(Path.of(PLANES_FILE_PATH));
    }

    @Override
    public String importPlanes() throws IOException, JAXBException {
        PlanesInputRootDto planesInputRootDto = xmlParser.fromFile(PLANES_FILE_PATH, PlanesInputRootDto.class);
        StringBuilder sb = new StringBuilder();

        List<String> registerNumbers = new ArrayList<>();

        List<Plane> planes = planesInputRootDto
                .getPlanes()
                .stream()
                .filter(planeInputDto -> {
                    boolean valid = validationUtil.isValid(planeInputDto);

                    if (registerNumbers.contains(planeInputDto.getRegisterNumber())) {
                        valid = false;
                    } else {
                        registerNumbers.add(planeInputDto.getRegisterNumber());
                    }

                    if (valid) {
                        sb.append(String.format("Successfully imported Plane %s",
                                planeInputDto.getRegisterNumber()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid Plane")
                                .append(System.lineSeparator());
                    }


                    return valid;
                })
                .map(planeInputDto -> modelMapper.map(planeInputDto, Plane.class))
                .collect(Collectors.toList());

        planeRepository.saveAll(planes);

        return sb.toString().trim();
    }

    @Override
    public Plane findByRegisterNumber(String registerNumber) {
        return planeRepository.findByRegisterNumber(registerNumber);
    }
}
