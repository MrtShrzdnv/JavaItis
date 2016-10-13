package dao;

import models.Owner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marat_2 on 12.10.2016.
 */
public class OwnersDaoJdbcImpl implements OwnersDao {
    private Connection connection;
    private static final String FIND_QUERY = "SELECT * FROM owners WHERE owner_id = ";
    private static final String GET_ALL_QUERY = "SELECT * FROM owners";
    private static final String DELETE_QUERY = "DELETE FROM owners WHERE owner_id = ?";
    private static final String UPDATE_QUERY = "UPDATE owners SET owner_id = ?, name = ?, age = ?, residence = ? WHERE car_id = ?";
    private static final String ADD_QUERY = "INSERT INTO owners (owner_id, name, age, residence) VALUES (?, ?, ?, ?)";

    public OwnersDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }
    public Owner find(int id) {
        try {
            ResultSet result = connection.createStatement().executeQuery(FIND_QUERY + id);
            Owner owner;
            if (result.next()) {
                owner = new Owner(result.getInt("owner_id"), result.getString("name"), result.getInt("age"), result.getString("residence"));
                return owner;
            }
            else
                return null;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Owner> getAll() {
        try {
            ResultSet result = connection.createStatement().executeQuery(GET_ALL_QUERY);
            Owner owner;
            List<Owner> owners = new ArrayList<Owner>();
            while (result.next()) {
                owner = new Owner(result.getInt("owner_id"), result.getString("name"), result.getInt("age"), result.getString("residence"));
                owners.add(owner);
            }
            return owners;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Owner owner) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setInt(1, owner.getId());
            preparedStatement.setString(2, owner.getName());
            preparedStatement.setInt(3, owner.getAge());
            preparedStatement.setString(4, owner.getResidence());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void add(Owner owner) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY);
            preparedStatement.setInt(1, owner.getId());
            preparedStatement.setString(2, owner.getName());
            preparedStatement.setInt(3, owner.getAge());
            preparedStatement.setString(4, owner.getResidence());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
