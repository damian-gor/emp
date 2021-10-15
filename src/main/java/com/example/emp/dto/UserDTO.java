package com.example.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String login;
    private Long id;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;

    private Double calculations;

}
