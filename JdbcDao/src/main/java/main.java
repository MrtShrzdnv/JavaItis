import dao.OwnersDao;
import dao.OwnersDaoJdbcImpl;
import factories.DaoFactory;
import factories.JdbcConnection;
import factories.ServiceFactory;
import models.Owner;
import services.OwnerService;
import services.OwnerServiceImpl;

import java.util.List;

/**
 * Created by KFU-user on 13.10.2016.
 */
class Main {
    public static void main(String[] args) {
        Owner owner = new Owner(1, "Marat", 22, "Kazan");
        OwnerService ownerService = ServiceFactory.getInstance().getOwnerService();
        owner = ownerService.findById(1);
        System.out.println(owner);
    }
}
