package RESTService;

import DomainApplication.AbonneeDAOFactory;
import DomainApplication.IAbonnee;
import DomainApplication.IAbonnement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
public class AbonneeService implements IAbonneeService {
    public IAbonnee getAbonneeByEmail( String email ) {
        return AbonneeDAOFactory.getAccessObject().findAbonneeMetEmail( email );
    }

    public IAbonnee getAbonneeById( int findId ) {
        return AbonneeDAOFactory.getAccessObject().findAbonneeById( findId );
    }

    public List<IAbonnee> getAbonneesThatAreSharing( IAbonnement sharedAbonnement ) {
        return AbonneeDAOFactory.getAccessObject().getAbonneesThatAreSharingAbonnement( sharedAbonnement );
    }

    public List<IAbonnee> getAllWithFilter( List<IAbonnee> filterAbonnees ) {
        List<IAbonnee> abonnees = AbonneeDAOFactory.getAccessObject().getAllAbonnees();
        List<IAbonnee> filteredList = new ArrayList<>();
        for( IAbonnee abonnee : abonnees ) {
            if ( !isInFilterList( abonnee , filterAbonnees ) ) {
                filteredList.add( abonnee );
            }
        }
        return filteredList;
    }

    private boolean isInFilterList( IAbonnee checkFor , List<IAbonnee> filterList ) {
        for( IAbonnee skipAbonee : filterList ) {
            if ( checkFor.getAbonneeId() == skipAbonee.getAbonneeId() ) {
                return true;
            }
        }
        return false;
    }

    public void createAbonnee( String naam , String achternaam , String email ) {
        AbonneeDAOFactory.getAccessObject().createAbonnee( naam , achternaam , email );
    }
}
