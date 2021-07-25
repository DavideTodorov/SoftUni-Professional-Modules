package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Picture;
import softuni.exam.instagraphlite.models.User;
import softuni.exam.instagraphlite.models.dtos.UserInputDto;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_FILE_PATH = "src/main/resources/files/users.json";

    private final UserRepository userRepository;
    private final PictureService pictureService;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PictureService pictureService, Gson gson,
                           ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.pictureService = pictureService;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean аreImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files
                .readString(Path.of(USER_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        String usersFile = readFromFileContent();
        StringBuilder sb = new StringBuilder();


        UserInputDto[] userInputDtos = gson.fromJson(usersFile, UserInputDto[].class);

        List<String> names = new ArrayList<>();

        List<User> users = Arrays.stream(userInputDtos)
                .filter(userInputDto -> {
                    boolean valid = validatorUtil.isValid(userInputDto);

                    Picture picture = pictureService.findPictureByPath(userInputDto.getProfilePicturePath());

                    if (picture == null) {
                        valid = false;
                    }

                    if (valid) {
                        sb.append(String.format("Successfully imported User: %s",
                                userInputDto.getUsername()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid User")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(userInputDto -> {
                    User user = modelMapper.map(userInputDto, User.class);
                    user.setPicture(pictureService.findPictureByPath(userInputDto.getProfilePicturePath()));
                    return user;
                })
                .collect(Collectors.toList());

        userRepository.saveAll(users);

        return sb.toString().trim();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        List<User> users = userRepository.findAllOrderedByPostCountDescAndUserId();

        StringBuilder sb = new StringBuilder();

        for (User user : users) {
            sb.append(String.format("User: %s\n" +
                    "Post count: %d", user.getUsername(), user.getPosts().size()))
                    .append(System.lineSeparator());

            user
                    .getPosts()
                    .stream()
                    .sorted(Comparator.comparingDouble(p -> p.getPicture().getSize()))
                    .forEach(p -> sb.append(String.format("==Post Details:\n" +
                            "----Caption: %s\n" +
                            "----Picture Size: %.2f", p.getCaption(), p.getPicture().getSize()))
                            .append(System.lineSeparator()));
        }


        return sb.toString().trim();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
