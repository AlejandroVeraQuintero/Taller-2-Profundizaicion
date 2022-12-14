package co.com.poli.userservice.service;

import co.com.poli.userservice.clientFeigh.BookingClient;
import co.com.poli.userservice.exception.UserException;
import co.com.poli.userservice.mapper.UserDtoToTask;
import co.com.poli.userservice.model.Booking;
import co.com.poli.userservice.persistence.entity.User;
import co.com.poli.userservice.persistence.repository.UserRepository;
import co.com.poli.userservice.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserDtoToTask usuarioDtoToTask;

    private final BookingClient bookingClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserDto userDto) {
       User user =  usuarioDtoToTask.map(userDto);
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        List<Booking> response = (List<Booking>) bookingClient.findByUserId(user.getId()).getData();
        if (response.size() != 0 ){
            throw new UserException("No se puede eliminar un usuario con reservas activas", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
