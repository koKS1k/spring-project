package com.artemkononov.webapplication.entities.security;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "t_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, message = "At least 4 characters")
    @NotEmpty(message = "User's name cannot be empty.")
    @NotBlank
    private String username;

    @Size(min = 4, message = "At least 4 characters")
    @NotBlank
    private String password;

    @Email(message = "Please provide a valid email")
    @NotBlank
    @Column(unique = true)
    private String email;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;




    public User(Long id,
                String username,
                String password,
                String email,
                Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    public String getAllRoles()
    {
        StringBuilder rolesString = new StringBuilder("");

        for (Role role : this.roles) {

            rolesString.append(role.getName().toString());
            rolesString.append(", ");
        }
        rolesString.delete(rolesString.length() - 2,rolesString.length() - 1);

        return rolesString.toString();
    }


}



