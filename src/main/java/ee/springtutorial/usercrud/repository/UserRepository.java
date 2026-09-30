package ee.springtutorial.usercrud.repository;

import ee.springtutorial.usercrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
