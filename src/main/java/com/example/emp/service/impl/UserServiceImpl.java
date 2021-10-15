package com.example.emp.service.impl;

import com.example.emp.dto.UserDTO;
import com.example.emp.exception.UserNotFoundException;
import com.example.emp.model.User;
import com.example.emp.model.db.RequestCounter;
import com.example.emp.repository.RequestCounterRepository;
import com.example.emp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${users-api.url:https://api.github.com/users/}")
    private String usersApiUrl;
    @Autowired
    private RequestCounterRepository requestCounterRepository;

    @Override
    public UserDTO getUserDTOByLogin(String login) {
        try {
            User user = restTemplate.getForObject(usersApiUrl + login, User.class);
            UserDTO userDTO = mapUserToUserDTO(user);
            increaseRequestCounter(login);
            return userDTO;
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == 404) {
                throw new UserNotFoundException(login);
            } else {
                throw e;
            }
        }
    }

    /**
     * Mapuje użytkownika z githuba na użytkownika zwracanego przez kontroler
     *
     * @param user
     * @return
     */
    private UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = UserDTO.builder()
                .login(user.getLogin())
                .id(user.getId())
                .name(user.getName())
                .type(user.getType())
                .avatarUrl(user.getAvatarUrl())
                .createdAt(user.getCreatedAt())
                .build();
        if (user.getFollowers() == 0) {
            userDTO.setCalculations(0d);
        } else {
            userDTO.setCalculations(((double) 6 / user.getFollowers() * (2 + user.getPublic_repos())));
        }
        return userDTO;
    }

    /**
     * Odnotowuje w bazie danych wyszukanie danego użytkownika
     *
     * @param login
     */
    private void increaseRequestCounter(String login) {
        RequestCounter requestCounter = requestCounterRepository.findById(login)
                .orElse(new RequestCounter(login, 0));
        requestCounter.setRequestCount(requestCounter.getRequestCount() + 1);
        requestCounterRepository.save(requestCounter);
    }
}
