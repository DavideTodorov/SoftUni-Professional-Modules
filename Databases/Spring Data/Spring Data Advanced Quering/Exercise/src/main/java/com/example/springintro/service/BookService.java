package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerThan, BigDecimal greaterThan);

    List<String> findAllByReleaseYearNotIn(String year);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<String> findAllByTitleContainingAWord(String word);

    List<String> findAllBYAuthorsLastNameStartsWith(String name);

    int countOfBooksWithTitlesLongerThan(int count);

    int countOfCopiesByAuthor(String author);

    String findBookInfoByTitle(String title);

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDate);

    int saveAll(Iterable<Book> books);

    int deleteByCopiesLessThan(Integer copies);
}
