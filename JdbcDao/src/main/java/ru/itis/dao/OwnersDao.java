package ru.itis.dao;

import ru.itis.models.Owner;

import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public interface OwnersDao {
    Owner find(int id);
    List<Owner> getAll();
    void delete(int id);
    void update(Owner owner);
    void add(Owner owner);
}
