package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "roleName")
    private String roleName;
    public Role(){
    }
    public Role(String roleName){
        this.roleName=roleName;
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) && Objects.equals(getRoleName(), role.getRoleName());
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
