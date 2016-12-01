package model;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class Chat {
    private Integer id;
    private String name;
    private Integer ownerId;
    private List<User> usersList;
    private List<Message> messages;

    public Chat() {
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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public static Builder newBuilder() {
        return new Chat().new Builder();
    }

    public class Builder{

         private Builder(){}

         public Builder setId(Integer id){
             Chat.this.id = id;
             return this;
         }
         public Builder setName(String name){
             Chat.this.name = name;
             return this;
         }
         public Builder setOwnerId(Integer owner_id){
             Chat.this.ownerId = owner_id;
             return this;
         }
         public Builder setUsersList(List<User> usersList){
             Chat.this.usersList = usersList;
             return this;
         }
         public Builder setMessages(List<Message> messages){
             Chat.this.messages = messages;
             return this;
         }
         public Chat build(){
             return Chat.this;
         }
     }
}
