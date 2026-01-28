package com.krushkov.virtualwallet.helpers;

public final class ValidationMessages {

    private ValidationMessages() {}

    public final static String USERNAME_LENGTH_ERROR = "Username must be between {min} and {max} symbols.";
    public final static String FIRST_NAME_LENGTH_ERROR = "First name must be between {min} and {max} symbols.";
    public final static String LAST_NAME_LENGTH_ERROR = "Last name must be between {min} and {max} symbols.";
    public final static String EMAIL_LENGTH_ERROR = "Email must be between {min} and {max} symbols.";
    public final static String PASSWORD_LENGTH_ERROR = "Password must be between {min} and {max} symbols.";

    public final static String USERNAME_NOT_NULL_ERROR = "Username is required.";
    public final static String FIRST_NAME_NOT_NULL_ERROR = "First name is required.";
    public final static String LAST_NAME_NOT_NULL_ERROR = "Last name is required.";
    public final static String EMAIL_NOT_NULL_ERROR = "Email is required.";
    public final static String PASSWORD_NOT_NULL_ERROR = "Password is required.";

    public final static String EMAIL_INVALID_ERROR = "Email is invalid.";
}
