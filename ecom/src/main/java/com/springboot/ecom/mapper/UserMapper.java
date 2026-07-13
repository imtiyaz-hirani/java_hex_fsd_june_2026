package com.springboot.ecom.mapper;

import com.springboot.ecom.dto.request.ExecutiveReqDto;
import com.springboot.ecom.enums.Role;
import com.springboot.ecom.model.User;

public class UserMapper {

    public static User convertDtoToEntity(String username, String password, Role role){

        User user = new User(); // this user has no id
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }
}
