package com.khudim.users;

/**
 * @author hudyshkin
 */
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String role() {
        return role;
    }

    public static UserRole findByRole(String role) {
        for (UserRole userRole : values()) {
            if (userRole.role().equals(role)) {
                return userRole;
            }
        }
        return null;
    }
}
