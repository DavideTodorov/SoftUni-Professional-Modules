package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerThan, BigDecimal greaterThan);

    @Query("select b.title from Book b where substring(b.releaseDate, 1, 4) not in :year")
    List<String> findAllByReleaseYearNotIn(String year);

    @Query("select b.title from Book b where lower(b.title) like %:word%")
    List<String> findAllByTitleContainingAWord(String word);

    @Query("select b.title from Book b where lower(b.author.lastName) like :name%")
    List<String> findAllBYAuthorsLastNameStartsWith(String name);

    @Query("select count(b.title) from Book b where length(b.title) > :count")
    int countOfBooksWithTitlesLongerThan(int count);

    @Query("select sum(b.copies) from Book b where " +
            "concat(b.author.firstName, ' ', b.author.lastName) = :author " +
            "group by b.author.id")
    int countOfCopiesByAuthor(String author);

    @Query("select b.title, b.editionType, b.ageRestriction, b.price " +
            "from Book b where b.title = :title")
    String findBookInfoByTitle(String title);



    List<Book> findAllByReleaseDateAfter(LocalDate releaseDate);

    @Modifying
    int deleteByCopiesLessThan(Integer copies);


}
