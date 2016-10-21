package RESTService;

import DomainApplication.IAbonnee;
import DomainApplication.IAbonnement;
import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * Created by Anders Egberts on 21/10/2016.
 */
@ImplementedBy(AbonneeService.class)
public interface IAbonneeService {
    IAbonnee getAbonneeByEmail(String email );

    IAbonnee getAbonneeById( int findId );

    List<IAbonnee> getAbonneesThatAreSharing(IAbonnement sharedAbonnement );

    List<IAbonnee> getAllWithFilter( List<IAbonnee> filterAbonnees );

    void createAbonnee( String naam , String achternaam , String email );
}
