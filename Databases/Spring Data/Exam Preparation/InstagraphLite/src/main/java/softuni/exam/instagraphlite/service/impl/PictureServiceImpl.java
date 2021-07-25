package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Picture;
import softuni.exam.instagraphlite.models.dtos.PictureInputDto;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURES_FILE_PATH = "src/main/resources/files/pictures.json";

    private final PictureRepository pictureRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public PictureServiceImpl(PictureRepository pictureRepository, ValidatorUtil validatorUtil,
                              ModelMapper modelMapper, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files
                .readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        String picturesData = readFromFileContent();
        StringBuilder sb = new StringBuilder();

        PictureInputDto[] pictureInputDtos = gson.fromJson(picturesData, PictureInputDto[].class);

        List<String> paths = new ArrayList<>();

        List<Picture> pictures = Arrays.stream(pictureInputDtos)
                .filter(pictureInputDto -> {
                    boolean valid = validatorUtil.isValid(pictureInputDto);


                    if (paths.contains(pictureInputDto.getPath())) {
                        valid = false;
                    } else if (valid) {
                        paths.add(pictureInputDto.getPath());
                    }


                    if (valid) {
                        sb.append(String.format("Successfully imported Picture, with size %.2f",
                                pictureInputDto.getSize()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid Picture")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(pictureInputDto -> modelMapper.map(pictureInputDto, Picture.class))
                .collect(Collectors.toList());

        pictureRepository.saveAll(pictures);

        return sb.toString().trim();
    }

    @Override
    public String exportPictures() {
        List<Picture> pictures = pictureRepository.findAllBySizeGreaterThanOrderBySizeAsc(30000);
        StringBuilder sb = new StringBuilder();

        for (Picture picture : pictures) {
            sb.append(String.format("%.2f - %s", picture.getSize(), picture.getPath()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public Picture findPictureByPath(String path) {
        return pictureRepository.findByPath(path);
    }
}
