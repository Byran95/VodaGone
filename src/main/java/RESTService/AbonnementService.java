package RESTService;

import DomainApplication.Abonnement;
import DomainApplication.AbonnementDAOFactory;
import DomainApplication.IAbonnement;
import DomainApplication.IAbonnementAccess;
import Util.ServerLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
public class AbonnementService {

    public List<IAbonnement> getAll() {
        ServerLogger.log( getClass() , "Service called upon." );
        IAbonnementAccess access = AbonnementDAOFactory.getAccessObject();
        ServerLogger.log( getClass() , "access: " + access );

        List<IAbonnement> results = access.getAllAbonnementen();
        List<IAbonnement> processedList = new ArrayList<>();
        for( IAbonnement abonnement : results ) {
            System.out.println( abonnement.getSoort() );
            Abonnement abo = new Abonnement( abonnement.getAbonneeId() , abonnement.getStartDatum() ,
                    abonnement.getVerdubbeld() , abonnement.getSoort() , abonnement.getStatus() );
            processedList.add( abo );
        }

        return processedList;
    }

    public List<IAbonnement> getByAbonnee( int abonneeId ) {
        return AbonnementDAOFactory.getAccessObject().findAbonnementenVanAbonnee( abonneeId );
    }
}
