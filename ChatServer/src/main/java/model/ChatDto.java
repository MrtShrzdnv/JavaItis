package model;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class ChatDto {
    private int id;
    private String name;
    private List<UserDto>  usersList;

    public ChatDto(int id, String name, List<UserDto> usersList) {
        this.id = id;
        this.name = name;
        this.usersList = usersList;
    }

    public ChatDto() {
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

    public List<UserDto> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserDto> usersList) {
        this.usersList = usersList;
    }
}
