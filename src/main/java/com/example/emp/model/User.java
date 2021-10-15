package com.example.emp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String login;
    private String name;
    private String type;
    @JsonAlias({"avatar_url"})
    private String avatarUrl;
    @JsonAlias({"created_at"})
    private String createdAt;
    private Integer followers;
    private Integer public_repos;

}
