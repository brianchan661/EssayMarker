package com.prolancer.essaymarker.type;

public enum Role {
    USER("user"),
    MARKER("marker"),
    ADMIN("admin");

    public String role;

    Role(String role){
        this.role = role;
    }
}
