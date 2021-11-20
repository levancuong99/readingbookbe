package k17.example.readingbook.repository;

import k17.example.readingbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByUserId(int userId);
    List<User> findAllBy();
}
