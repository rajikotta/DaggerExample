package com.raji.daggerexample.main;

/**
 * Created by Raji on 7/17/20.
 * Golden Scent
 */
public class NetworkResource<T> {
    public T data;
    public String errorMessage;
    public ResourceStatus status;


    public NetworkResource(T data, String errorMessage, ResourceStatus status) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.status = status;
    }


    public static <T> NetworkResource loading(T data) {
        return new NetworkResource(data, null, ResourceStatus.LOADING);
    }

    public static <T> NetworkResource error(String errror) {
        return new NetworkResource(null, errror, ResourceStatus.ERROR);
    }

    public static <T> NetworkResource success(T data) {
        return new NetworkResource(data, null, ResourceStatus.SUCCESS);
    }

    public enum ResourceStatus {
        LOADING, ERROR, SUCCESS
    }
}
