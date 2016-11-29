package model;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class Message {
    private Integer id;
    private String text;
    private Integer userId;
    private Integer chatId;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public static Builder newBuilder() {
        return new Message().new Builder();
    }

    public class Builder{

        private Builder(){}

        public Builder setId(Integer id){
            Message.this.id = id;
            return this;
        }
        public Builder setText(String text){
            Message.this.text = text;
            return this;
        }
        public Builder setUserId(Integer userId){
            Message.this.userId = userId;
            return this;
        }
        public Builder setChatId(Integer chatId){
            Message.this.chatId = chatId;
            return this;
        }
        public Message build(){
            return Message.this;
        }
    }
}
