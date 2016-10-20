package RESTService;

import DomainApplication.AbonnementDAOFactory;
import DomainApplication.IAbonnement;

import java.util.List;

/**
 * Created by Anders Egberts on 14/10/2016.
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
}
