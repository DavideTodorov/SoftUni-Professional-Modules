package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.instagraphlite.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query("select u from User u order by u.posts.size desc, u.id")
    List<User> findAllOrderedByPostCountDescAndUserId();
}
