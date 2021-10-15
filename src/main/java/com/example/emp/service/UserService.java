package com.example.emp.service;

import com.example.emp.dto.UserDTO;

public interface UserService {
    UserDTO getUserDTOByLogin(String login);
}
