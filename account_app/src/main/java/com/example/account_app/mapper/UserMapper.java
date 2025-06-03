package com.example.account_app.mapper;

import com.example.account_app.dto.UserDTO;
import com.example.account_app.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        return new UserDTO(
                user.getName(),
                user.getLogin()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) return null;

        User user = new User();
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        return user;
    }

    public static List<UserDTO> toDTOList(List<User> users) {
        if (users == null) return List.of();

        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
