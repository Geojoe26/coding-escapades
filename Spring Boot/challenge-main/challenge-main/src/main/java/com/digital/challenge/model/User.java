package com.digital.challenge.model;

import com.digital.challenge.controller.dto.UserDto;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private String email;

    public UserDto toDto(){
     return UserDto.builder()
             .id(this.id)
             .firstName(this.firstName)
             .lastName(this.lastName)
             .email(this.email)
             .jobTitle(this.jobTitle)
             .build();
    }

    public User value(UserDto fromDto){
        this.firstName = fromDto.getFirstName();
        this.lastName = fromDto.getLastName();
        this.jobTitle = fromDto.getJobTitle();
        this.email = fromDto.getEmail();
        return this;
    }
}
