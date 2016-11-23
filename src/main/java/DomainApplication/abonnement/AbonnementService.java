package DomainApplication.abonnement;

import DomainApplication.*;
import DomainApplication.abonnee.AbonneeDAOFactory;
import DomainApplication.abonnement.AbonnementDAOFactory;
import DomainApplication.abonnement.AbonnementStatus;
import DomainApplication.abonnement.IAbonnementAccess;

import java.util.List;

/**
 * Created by Bryan van Elden on 14/10/2016.
 */
public class AbonnementService {


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

    public void shareWith( IAbonnement abonnementToShare , IAbonnee recepient ) {
        IAbonnee delendeAbonnee = AbonneeDAOFactory.getAccessObject().findAbonneeById( abonnementToShare.getAbonneeId() );
        AbonnementDAOFactory.getAccessObject().shareAbonnement( recepient , delendeAbonnee , abonnementToShare.getDienst() );
    }
}