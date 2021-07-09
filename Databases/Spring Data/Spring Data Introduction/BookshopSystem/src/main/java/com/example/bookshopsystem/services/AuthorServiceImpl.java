package com.example.bookshopsystem.services;

import com.example.bookshopsystem.entities.Author;
import com.example.bookshopsystem.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final String AUTHOR_FILE_PATH = "src/main/resources/dbData/authors.txt";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() {
        if (authorRepository.count() > 0){
            return;
        }

        try {
            Files.readAllLines(Path.of
                    (AUTHOR_FILE_PATH))
                    .forEach(authorEntry -> {
                        String[] tokens = authorEntry.split("\\s+");
                        String firstName = tokens[0];
                        String lastName = tokens[1];

                        Author author = new Author(firstName, lastName);

                        authorRepository.save(author);
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
