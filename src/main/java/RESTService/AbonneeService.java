package RESTService;

import DomainApplication.AbonneeDAOFactory;
import DomainApplication.IAbonnee;
import DomainApplication.IAbonneeAccess;
import Util.ServerLogger;

import java.util.List;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
public class AbonneeService {

    private IAbonneeAccess access;

    public List<IAbonnee> getAll() {
        ServerLogger.log( getClass() , "Service called upon." );
        access = AbonneeDAOFactory.getAccessObject();
        ServerLogger.log( getClass() , "access: " + access );
        return access.getAllAbonnees();
    }

    public IAbonnee findAbonnee(int abonneeId) {
        return access.findAbonneeMetId(abonneeId);
    }
}
