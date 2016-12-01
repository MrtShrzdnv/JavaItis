package model;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class User {
    private Integer id;
    private String name;
    private String login;
    private String hash_password;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder{

        private Builder(){}

        public Builder setId(Integer id){
            User.this.id = id;
            return this;
        }
        public Builder setName(String name){
            User.this.name = name;
            return this;
        }
        public Builder setLogin(String owner_id){
            User.this.login = owner_id;
            return this;
        }
        public Builder setHashPassword(String hashPassword){
            User.this.hash_password = hashPassword;
            return this;
        }

        public User build(){
            return User.this;
        }
    }
}
