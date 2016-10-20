package DomainApplication;

import java.util.List;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonnementAccess {

    List<IAbonnement> getAllAbonnementen();

    List<IAbonnement> findAbonnementenVanAbonnee(int id);

    void updateAbonnementSoort(AbonnementSoort soort, IAbonnee abonnee, IDienst dienst);

    void updateAbonnementStatus(AbonnementStatus status, IAbonnee abonnee, IDienst dienst);

    void updateIsVerdubbeld(boolean isVerdubbeld, IAbonnee abonnee, IDienst dienst);

    void createAbonnement( IAbonnement abonnement );

    AbonnementSoort getEnumSoort(String soort);

    AbonnementStatus getEnumStatus(String status);
}
