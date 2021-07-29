package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.models.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("select c from Car c order by c.pictures.size desc, c.make asc ")
    List<Car> findAllAndOrderByPicturesCountDescAndMakeAsc();
}
