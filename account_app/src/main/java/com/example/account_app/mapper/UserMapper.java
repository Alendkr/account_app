package com.example.account_app.mapper;

import com.example.account_app.dto.UserDTO;
import com.example.account_app.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getMoney()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        // Важно: пароль с фронта должен приходить отдельно!
        user.setMoney(userDTO.getMoney());
        return user;
    }

    public static List<UserDTO> toDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
