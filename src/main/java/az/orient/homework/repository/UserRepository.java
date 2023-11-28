package az.orient.homework.repository;

import az.orient.homework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByActive(Integer active);

    User findUserById (Long id);

    User findUserByToken(String token);
}
