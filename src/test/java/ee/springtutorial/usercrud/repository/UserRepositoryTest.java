package ee.springtutorial.usercrud.repository;

import ee.springtutorial.usercrud.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void savesAndFindsUser() {
        User saved = userRepository.save(new User("Mari", "Tamm", "mari@example.com"));

        assertThat(saved.getId()).isNotNull();
        assertThat(userRepository.findById(saved.getId()))
                .get()
                .extracting(User::getEmail)
                .isEqualTo("mari@example.com");
    }
}
