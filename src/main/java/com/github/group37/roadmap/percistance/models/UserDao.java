package com.github.group37.roadmap.percistance.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "users", schema = "roadmap_project")
public class UserDao {

    @Id
    @Column(name = "username", nullable = false, unique = true, length = 36)
    @Size(min = 3, max = 36)
    private String username;

    @Column(name = "password", nullable = false, unique = false, length = 100)
    @Size(min = 6, max = 100)
    private String password;

    @Column(name = "enabled", nullable = false, unique = false)
    private boolean enabled;

    public UserDao(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    public UserDao() {
        // ignore, created to keep spring auth happy
    }

    public UserDao(String abdi, String smith, boolean b) {}

    @Override
    public String toString() {
        return "User{" + ", name='" + username + '\'' + ", password='" + password + '\'' + '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
