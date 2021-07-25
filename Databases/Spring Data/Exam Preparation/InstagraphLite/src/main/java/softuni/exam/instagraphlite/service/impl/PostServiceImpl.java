package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Post;
import softuni.exam.instagraphlite.models.dtos.PostInputDto;
import softuni.exam.instagraphlite.models.dtos.PostInputRootDto;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidatorUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private static final String POSTS_FILE_PATH = "src/main/resources/files/posts.xml";

    private final PostRepository postRepository;
    private final UserService userService;
    private final PictureService pictureService;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, UserService userService,
                           PictureService pictureService, ValidatorUtil validatorUtil,
                           XmlParser xmlParser, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.pictureService = pictureService;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }


    @Override
    public Boolean аreImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POSTS_FILE_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        PostInputRootDto postInputRootDto = xmlParser.fromFile(POSTS_FILE_PATH, PostInputRootDto.class);
        StringBuilder sb = new StringBuilder();


        List<Post> users = postInputRootDto
                .getPosts()
                .stream()
                .filter(postInputDto -> {
                    boolean valid = validatorUtil.isValid(postInputDto);

                    if (userService.findByUsername(postInputDto.getUser().getUsername()) == null ||
                            pictureService.findPictureByPath(postInputDto.getPicture().getPath()) == null) {
                        valid = false;
                    }

                    if (valid) {
                        sb.append(String.format("Successfully imported Post, made by %s",
                                postInputDto.getUser().getUsername()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid Post")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(postInputDto -> {
                    Post post = modelMapper.map(postInputDto, Post.class);
                    post.setPicture(pictureService.findPictureByPath(postInputDto.getPicture().getPath()));
                    post.setUser(userService.findByUsername(postInputDto.getUser().getUsername()));
                    return post;
                })
                .collect(Collectors.toList());

        postRepository.saveAll(users);

        return sb.toString().trim();
    }
}
