package com.khudim.users;

import javax.persistence.*;

/**
 * Created by Beaver.
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByName",
                query = "SELECT user FROM User user WHERE user.name = :name "),
        @NamedQuery(name = "User.findAll",
                query = "SELECT user FROM User user")
})
public class User {
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public User() {
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name = "role", nullable = false)
    public String getRole() {
        return role.role();
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Transient
    public UserRole getUserRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = UserRole.findByRole(role);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 71;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
