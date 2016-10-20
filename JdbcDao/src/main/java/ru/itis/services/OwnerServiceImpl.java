package ru.itis.services;

import ru.itis.dao.OwnersDao;
import ru.itis.models.Owner;

import java.util.List;

import static ru.itis.utils.Verifier.verifyUserExist;


/**
 * Created by KFU-user on 13.10.2016.
 */
public class OwnerServiceImpl implements OwnerService {
    public OwnerServiceImpl(OwnersDao ownersDao) {
        this.ownersDao = ownersDao;
    }

    private OwnersDao ownersDao;

    public Owner findById(int id) {
        return this.ownersDao.find(id);
    }

    public void updateOwner(Owner owner) {
        verifyUserExist(owner.getId());
        this.ownersDao.update(owner);
    }
    public void addOwner(Owner owner){
        this.ownersDao.add(owner);
    }

    public List<Owner> getAll() {
        return this.ownersDao.getAll();
    }
}
