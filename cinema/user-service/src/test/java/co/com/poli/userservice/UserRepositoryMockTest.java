package co.com.poli.userservice;

import co.com.poli.userservice.persistence.entity.User;
import co.com.poli.userservice.persistence.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class UserRepositoryMockTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void when_findById_return_ListUsers() {
        long id = 1;
        User user = User.builder()
                .id(id)
                .name("Alejandro")
                .lastname("Vera").build();

        userRepository.save(user);
        Optional<User> userDt = userRepository.findById(id);
        User  userData = userDt.orElse(null);
        Assertions.assertThat(userData.getId()).isEqualTo(id);
    }
}
