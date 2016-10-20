package DomainApplication;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonneeAccess {

    List<IAbonnee> getAllAbonnees();
    List<IAbonnee> getAbonneesThatAreSharingAbonnement( IAbonnement sharedAbonnement );

    IAbonnee findAbonneeMetEmail(String email);
    IAbonnee findAbonneeById( int  findId );

    void createAbonnee(String naam, String achternaam, String emailadres);
}
