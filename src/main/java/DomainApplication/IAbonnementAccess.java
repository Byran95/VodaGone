package DomainApplication;

import java.util.List;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonnementAccess {

    List<IAbonnement> getAllAbonnementen();

    List<IAbonnement> findAbonnementenVanAbonnee(int id);

    void updateAbonnementSoort(AbonnementSoort soort, IAbonnee abonnee, IDienst dienst);

    void getUpdateAbonnementStatus(AbonnementStatus status, IAbonnee abonnee, IDienst dienst);

    AbonnementSoort getEnumSoort(String soort);

    AbonnementStatus getEnumStatus(String status);
}
