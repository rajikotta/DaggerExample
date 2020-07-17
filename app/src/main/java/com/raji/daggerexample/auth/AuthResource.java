package com.raji.daggerexample.auth;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public class AuthResource<T> {

    public T data;

    public String errorMsg;

    public Status status;

    public AuthResource(T data, String errorMsg, Status status) {
        this.data = data;
        this.errorMsg = errorMsg;
        this.status = status;
    }

    public static AuthResource error(String errorMsg) {
        return new AuthResource<>(null, errorMsg, Status.ERROR);
    }

    public static <V> AuthResource loading(V data) {
        return new AuthResource<>(data, null, Status.LOADING);
    }

    public static <V> AuthResource authenticated(V data) {
        return new AuthResource<>(data, null, Status.AUTHENTICATED);
    }

    public static <V> AuthResource logout() {
        return new AuthResource<>(null, null, Status.LOGOUT);
    }

    public enum Status {

        ERROR,
        LOADING,
        AUTHENTICATED,
        LOGOUT
    }

}
