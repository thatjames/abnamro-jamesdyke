package xyz.slimjim.hungrytales.web.converter;

import xyz.slimjim.hungrytales.common.auth.User;
import xyz.slimjim.hungrytales.web.dto.UserDTO;

public class UserItemDTOConverter implements DataObjectDTOConverter<User, UserDTO>{

    @Override
    public UserDTO fromItemToDTO(User item) {
        UserDTO userDto = new UserDTO();
        userDto.setId(item.getId());
        userDto.setUsername(item.getUsername());
        userDto.setName(item.getName());
        userDto.setSurname(item.getSurname());
        return userDto;
    }

    @Override
    public User fromDTOToItem(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        return user;
    }
}
