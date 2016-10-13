package services;

import models.Owner;

/**
 * Created by KFU-user on 13.10.2016.
 */
public interface OwnerService {
    Owner findById(int id);
    void updateOwner(Owner owner);
}
