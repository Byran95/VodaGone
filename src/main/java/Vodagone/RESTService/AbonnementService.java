package Vodagone.RESTService;

import Vodagone.DomainApplication.*;

import java.util.List;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
public class AbonnementService implements IAbonnementService {
    /*
    Directe aanroep methoden voor communicatie in de container (Eis IO1)
     */
    public List<IAbonnement> getAll() {
        return AbonnementDAOFactory.getAccessObject().getAllAbonnementen();
    }

    public List<IAbonnement> getByAbonnee( int abonneeId ) {
        return AbonnementDAOFactory.getAccessObject().findAbonnementenVanAbonnee( abonneeId );
    }

    public void createAbonnement( IAbonnement abonnement ) {
        AbonnementDAOFactory.getAccessObject().createAbonnement( abonnement );
    }

    public void updateVerdubbeld(boolean verdubbeld, int abonneeId, String bedrijf, String naam) {
        AbonnementDAOFactory.getAccessObject().updateIsVerdubbeld(verdubbeld, abonneeId, bedrijf, naam);
    }

    public void cancelSubscription(int abonneeId, String bedrijf, String naam) {
        IAbonnementAccess access = AbonnementDAOFactory.getAccessObject();

        IAbonnement abonnement = access.findAbonnement(abonneeId, bedrijf, naam);

        access.updateAbonnementStatus(AbonnementStatus.OPGEZEGD, abonnement);
    }

    public IAbonnement getByOwnerCompanyandName( int abonneeId , String company , String name ) {
        return AbonnementDAOFactory.getAccessObject().findAbonnement(abonneeId, company, name);
    }

    public boolean shareWith( IAbonnement abonnementToShare , IAbonnee recepient ) {
        IAbonnee delendeAbonnee = AbonneeDAOFactory.getAccessObject().findAbonneeById( abonnementToShare.getAbonneeId() );
        return AbonnementDAOFactory.getAccessObject().shareAbonnement( recepient , delendeAbonnee , abonnementToShare.getDienst() );
    }
}
