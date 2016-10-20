package RESTService;

import DomainApplication.AbonneeDAOFactory;
import DomainApplication.IAbonnee;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
public class AbonneeService {
    public IAbonnee getAbonneeByEmail( String email ) {
        return AbonneeDAOFactory.getAccessObject().findAbonneeMetEmail( email );
    }

    public void createAbonnee( String naam , String achternaam , String email ) {
        AbonneeDAOFactory.getAccessObject().createAbonnee( naam , achternaam , email );
    }
}
