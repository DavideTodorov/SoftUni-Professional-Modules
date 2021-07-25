package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.instagraphlite.models.Picture;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Integer> {

    Picture findByPath(String path);

    List<Picture> findAllBySizeGreaterThanOrderBySizeAsc(double size);
}
