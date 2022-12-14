package co.com.poli.userservice;


import co.com.poli.userservice.clientFeigh.BookingClient;
import co.com.poli.userservice.mapper.UserDtoToTask;
import co.com.poli.userservice.persistence.entity.User;
import co.com.poli.userservice.persistence.repository.UserRepository;
import co.com.poli.userservice.service.UserService;
import co.com.poli.userservice.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceMockTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private UserDtoToTask userDtoToTask;
    private BookingClient bookingClient;

    @BeforeEach
    public void begin() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository,userDtoToTask,bookingClient);

        User user = User.builder()
                .id(5L)
                .name("Test")
                .lastname("Test")
                .build();
        Mockito.when(userRepository.findById(5L)).thenReturn(Optional.of(user));
    }

    @Test
    public void when_findById_return_User() {
        User user = userService.findById(5L);
        Assertions.assertThat(user.getName()).isEqualTo("Test");
    }

}
