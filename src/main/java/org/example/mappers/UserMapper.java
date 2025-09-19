package org.example.mappers;

import org.example.dtos.UserDTO;
import org.example.models.User;

public class UserMapper {

    public static User toEntity(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());

        return user;
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getCreated_at()
        );

    }

}
