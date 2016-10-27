package models;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class Car {
    private int id;
    private String number;
    private int ownerId;

    public Car(int id, String number, int ownerId) {
        this.id = id;
        this.number = number;
        this.ownerId = ownerId;
    }

    public Car(String number, int ownerId) {
        this.number = number;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String toString(){
        return "id = " + id + " number = " + number + " ownerId = " + ownerId;
    }
}
