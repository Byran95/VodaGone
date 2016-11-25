package oose.dea.services.local;


import com.google.inject.Inject;
import oose.dea.domain.AbonneeDAOFactory;
import oose.dea.domain.IAbonnee;
import oose.dea.domain.IAbonneeAccess;
import oose.dea.domain.IAbonnement;
import oose.dea.services.IAbonneeService;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
//@Singleton
public class AbonneeService implements IAbonneeService {

    @Inject
    private IAbonneeAccess dataAccessObject;

    public AbonneeService() {
    }
    /*
    Directe aanroep methoden voor communicatie in de container (Eis IO1)
     */
    @Override
    public IAbonnee getAbonneeByEmail(String email ) {
        return dataAccessObject.findAbonneeMetEmail( email );
    }

    @Override
    public IAbonnee getAbonneeById( int findId ) {
        return dataAccessObject.findAbonneeById( findId );
    }

    @Override
    public List<IAbonnee> getAbonneesThatAreSharing( IAbonnement sharedAbonnement ) {
        return dataAccessObject.getAbonneesThatAreSharingAbonnement( sharedAbonnement );
    }

    @Override
    public List<IAbonnee> getAllWithFilter( List<IAbonnee> filterAbonnees ) {
        List<IAbonnee> abonnees = dataAccessObject.getAllAbonnees();
        List<IAbonnee> filteredList = new ArrayList<>();
        for( IAbonnee abonnee : abonnees ) {
            if ( !isInFilterList( abonnee , filterAbonnees ) ) {
                filteredList.add( abonnee );
            }
        }
        return filteredList;
    }

    @Override
    public void createAbonnee(IAbonnee newAbonnee) {
        dataAccessObject.createAbonnee( newAbonnee.getNaam() , newAbonnee.getAchternaam() , newAbonnee.getEmailadres() );
    }

    private boolean isInFilterList( IAbonnee checkFor , List<IAbonnee> filterList ) {
        for( IAbonnee skipAbonee : filterList ) {
            if ( checkFor.getAbonneeId() == skipAbonee.getAbonneeId() ) {
                return true;
            }
        }
        return false;
    }
}
