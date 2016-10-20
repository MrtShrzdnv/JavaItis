package ru.itis.services;

import ru.itis.models.Owner;

import java.util.List;

/**
 * Created by KFU-user on 13.10.2016.
 */
public interface OwnerService {
    Owner findById(int id);
    void updateOwner(Owner owner);
    void addOwner(Owner owner);
    List<Owner> getAll();
}
