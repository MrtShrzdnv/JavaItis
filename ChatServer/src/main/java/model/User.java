package model;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String hash_password;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hash_password;
    }

    public void setHashPassword(String hash_password) {
        this.hash_password = hash_password;
    }
}
