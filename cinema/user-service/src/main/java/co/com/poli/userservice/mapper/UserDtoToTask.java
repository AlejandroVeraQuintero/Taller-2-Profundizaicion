package co.com.poli.userservice.mapper;

import co.com.poli.userservice.persistence.entity.User;
import co.com.poli.userservice.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToTask implements IMapper<UserDto, User> {
    @Override
    public User map(UserDto in) {
        User user = new User();
        user.setId(in.getId());
        user.setName(in.getName());
        user.setLastname(in.getLastname());
        return user;
    }
}
