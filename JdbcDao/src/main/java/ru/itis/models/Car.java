package ru.itis.models;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Car {
    private int id;
    private int mileage;

    public int getId() {
        return id;
    }

    public int getMileage(){
        return mileage;
    }


    public Car(int id, int mileage) {
        this.id = id;
        this.mileage = mileage;
    }
}
