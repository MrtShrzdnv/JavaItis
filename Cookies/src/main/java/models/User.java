package models;

import java.util.List;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private String token;
    private List<Car> cars;

    public User(int id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(int id, String name, String login, String password, String token) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.token = token;
        this.login = login;
    }

    public User(String name, String login, String password, String token) {
        this.name = name;
        this.login = login;

        this.password = password;
        this.token = token;
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String toString(){

        String result =  "id = " + id + " login = " + login + " name = " + name + "\n";
        for(Car car : cars) {
            result += "   " + car.toString() + "\n";
        }
        return result;
    }
}
