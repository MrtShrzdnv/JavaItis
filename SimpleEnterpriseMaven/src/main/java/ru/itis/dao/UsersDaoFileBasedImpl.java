package ru.itis.dao;

import com.google.common.base.Splitter;
import ru.itis.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UsersDaoFileBasedImpl implements UsersDao {

    private BufferedReader fileReader;
    private String fileName;

    public UsersDaoFileBasedImpl() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\src\\main\\java\\ru\\itis\\resources\\fileNames"));
            String file = properties.getProperty("fileName");
            //String fileName = properties.getProperty("fileName");
            this.fileName = file;
            fileReader = new BufferedReader(new FileReader("C:\\Users\\KFU-user\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\users.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            String currentLine = fileReader.readLine();
            while (currentLine != null) {
                User currentUser = parseStringAsUser(currentLine);
                result.add(currentUser);
                currentLine = fileReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("SomeError");
        }
        return result;
    }

    private User parseStringAsUser(String line) {
        Splitter splitter = Splitter.on(" ");

        List<String> lines = splitter.splitToList(line);

        String name = lines.get(0);
        String password = lines.get(1);
        int age = Integer.parseInt(lines.get(2));
        int id = Integer.parseInt(lines.get(3));

        return new User(name, password, age, id);
    }

    public User get(int userId){
        try {
            String currentLine = fileReader.readLine();
            while (currentLine != null) {
                User currentUser = parseStringAsUser(currentLine);
                if (currentUser.getId() == userId)
                    return currentUser;
                currentLine = fileReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("SomeError");
        }
        return new User("SomeName", "SomePassword", 22, userId);
    }

    public void save(User user){
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(fileName, true));
            String userString = user.toString();
            bf.write(userString);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void delete(int userId){

        try {
            List<User> users = getAll();
            BufferedWriter bf = new BufferedWriter(new FileWriter(fileName, false));
            bf.write("");
            for(User currentUser : users){
                if (!(currentUser.getId() == userId))
                    save(currentUser);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
