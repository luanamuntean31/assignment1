package org.launchcode.models;

public enum UserType {

    ADMIN("admin"),
    USER("user");


    private final String displayType;

    UserType(String displayType) {
        this.displayType = displayType;
    }

    public String getDisplayType() {
        return displayType;
    }



}
