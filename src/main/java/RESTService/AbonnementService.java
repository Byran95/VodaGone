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

        System.out.println("Naam: " + naam + "\n" +
            "AbonneeId: " + abonneeId + "\n" +
            "verdubbeld: " + verdubbeld + "\n" +
            "Bedrijf: " + bedrijf);

        access = AbonnementDAOFactory.getAccessObject();
        access.updateIsVerdubbeld(verdubbeld, abonneeId, bedrijf, naam);
    }
}