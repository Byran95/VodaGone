package RESTService;

import DomainApplication.*;
import Util.ServerLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
public class AbonnementService {
    private IAbonnementAccess access;

    public List<IAbonnement> getAll() {
        ServerLogger.log( getClass() , "Service called upon." );
        access = AbonnementDAOFactory.getAccessObject();
        ServerLogger.log( getClass() , "access: " + access );
        for (IAbonnement abo : access.getAllAbonnementen()) {
            System.out.println("Verdubbeld in service: " + abo.getVerdubbeld());
        }
        return access.getAllAbonnementen();
    }

    public void updateVerdubbeld(boolean verdubbeld, int abonneeId, String bedrijf, String naam) {

        access = AbonnementDAOFactory.getAccessObject();
        access.updateIsVerdubbeld(verdubbeld, abonneeId, bedrijf, naam);
    }

    public void cancelSubscription(int abonneeId, String bedrijf, String naam) {
        access = AbonnementDAOFactory.getAccessObject();

        IAbonnement abonnement = access.findAbonnement(abonneeId, bedrijf, naam);

        access.updateAbonnementStatus(AbonnementStatus.OPGEZEGD, abonnement);
    }
}