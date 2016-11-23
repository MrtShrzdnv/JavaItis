package model;

/**
 * Created by Marat_2 on 23.11.2016.
 */
public class MessageDto {
    private String text;
    private String author;

    public MessageDto(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public MessageDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
