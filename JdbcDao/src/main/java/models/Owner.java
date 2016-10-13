package models;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Owner {
    public Owner(int id, String name, int age, String residence) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.residence = residence;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getResidence() {
        return residence;
    }

    private int id;

    public int getId() {
        return id;
    }

    private String name;
    private  int age;
    private String residence;
}