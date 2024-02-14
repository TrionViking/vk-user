package com.vk.user.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vk.user.constant.EAccountStatus;

import com.vk.user.model.AccountEntity;
import com.vk.user.model.AdminEntity;
import com.vk.user.model.RoleEntity;
import com.vk.user.model.UserEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AuthUser implements UserDetails {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean sex;
    private String avatar;
    private String email;
    private String username;
    @JsonIgnore
    private String password;
    private EAccountStatus status;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public AuthUser(AccountEntity account, List<RoleEntity> roles) {
        super();
        this.id = account.getId();
        this.userId = account.getUserId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.email = account.getEmail();
        this.status = account.getStatus();

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getCode()));
        });
        this.authorities = authorities;
    }

    public void setUserInfo(UserEntity user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.sex = user.getSex();
        this.avatar = user.getAvatar();
    }

    public void setAdminInfo(AdminEntity admin) {
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.phoneNumber = admin.getPhoneNumber();
        this.sex = admin.getSex();
        this.avatar = admin.getAvatar();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !EAccountStatus.BLOCK.equals(this.status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return EAccountStatus.ACTIVE.equals(this.status);
    }
}
