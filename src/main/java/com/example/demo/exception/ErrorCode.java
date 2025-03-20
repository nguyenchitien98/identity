package com.example.demo.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXIST(1002, "User exist"),
    USERNAME_INVALID(1003, "Username must be at lest 3 character"),
    PASSWORD_INVALID(1004, "Password must be at lest 8 character"),
    USER_NOT_EXIST(1005, "User not exist"),
    UNAUTHENTICATED(1006, "Unauthenticated"),


    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
