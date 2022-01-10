package com.nurcan.stocksservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nurcan.stocksservice.dto.UserDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_ROLES",
            joinColumns =  @JoinColumn(name ="USER_ID"),inverseJoinColumns= @JoinColumn(name="ROLE_ID"))
    private Set<Role> roles;

    public UserDto toUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setEmail(this.email);
        userDto.setFirstName(this.firstName);
        userDto.setLastName(this.lastName);
        userDto.setUsername(this.username);
        userDto.setRole(this.roles.stream().map(role -> role.getName().toString()).collect(Collectors.toList()));
        return userDto;
    }
}
