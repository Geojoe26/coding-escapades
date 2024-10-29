package com.digital.challenge.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private String email;
}