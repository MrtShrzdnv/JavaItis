package dto;

/**
 * Created by Marat_2 on 23.11.2016.
 */
public class MessageDto {
    private int id;
    private String text;
    private String userName;

    public MessageDto(String text, String userName) {
        this.text = text;
        this.userName = userName;
    }

    public MessageDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
