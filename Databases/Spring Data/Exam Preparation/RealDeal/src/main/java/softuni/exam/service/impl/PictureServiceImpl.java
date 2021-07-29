package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Picture;
import softuni.exam.models.dtos.PictureInputDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURES_FILE_PATH = "src/main/resources/files/json/pictures.json";

    private final PictureRepository pictureRepository;
    private final CarService carService;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, CarService carService, Gson gson,
                              ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.carService = carService;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files
                .readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        String picturesFromFile = readPicturesFromFile();

        PictureInputDto[] pictureInputDtos = gson.fromJson(picturesFromFile, PictureInputDto[].class);

        List<String> names = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        List<Picture> pictures = Arrays.stream(pictureInputDtos)
                .filter(pictureInputDto -> {
                    boolean valid = validationUtil.isValid(pictureInputDto);

                    if (names.contains(pictureInputDto.getName())) {
                        valid = false;
                    } else {
                        names.add(pictureInputDto.getName());
                    }

                    if (valid) {
                        sb.append(String.format("Successfully import picture - %s",
                                pictureInputDto.getName()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid picture")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(pictureInputDto -> {
                    Picture picture = modelMapper.map(pictureInputDto, Picture.class);
                    picture.setCar(carService.findCarById(pictureInputDto.getCar()));
                    return picture;
                })
                .collect(Collectors.toList());

        pictureRepository.saveAll(pictures);

        return sb.toString().trim();
    }
}
