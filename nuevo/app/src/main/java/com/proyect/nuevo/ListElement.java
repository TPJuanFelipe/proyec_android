package com.proyect.nuevo;
import java.io.Serializable;

public class ListElement implements Serializable {
    private String color;
    private String name;
    private String city;
    private String status;
    private String activityType;

    public ListElement(String color, String name, String city, String status, String activityType) {
        this.color = color;
        this.name = name;
        this.city = city;
        this.status = status;
        this.activityType = activityType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
