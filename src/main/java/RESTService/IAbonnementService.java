package RESTService;

import DomainApplication.*;
import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * Created by Anders Egberts on 21/10/2016.
 */
@ImplementedBy(AbonnementService.class)
public interface IAbonnementService {
    List<IAbonnement> getAll();

    List<IAbonnement> getByAbonnee( int abonneeId );

    void createAbonnement( IAbonnement abonnement );

    void updateVerdubbeld(boolean verdubbeld, int abonneeId, String bedrijf, String naam);

    void cancelSubscription(int abonneeId, String bedrijf, String naam);

    IAbonnement getByOwnerCompanyandName( int abonneeId , String company , String name );

    boolean shareWith( IAbonnement abonnementToShare , IAbonnee recepient );
}
