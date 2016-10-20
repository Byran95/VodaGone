package DomainApplication;

import java.util.List;

/**
 * Created by Bryan van Elden on 12/10/2016.
 */
public interface IAbonnementAccess {

    IAbonnement findAbonnement(int abonneeId, String bedrijf, String naam);

    List<IAbonnement> getAllAbonnementen();

    List<IAbonnement> findAbonnementenVanAbonnee(int id);

    void updateAbonnementSoort(AbonnementSoort soort, IAbonnement abonnement);

    void updateAbonnementStatus(AbonnementStatus status, IAbonnement abonnement);

    void updateIsVerdubbeld(boolean isVerdubbeld, int abonneeId, String bedrijf, String naam);

    boolean isAbonnementDelenToegestaan(IAbonnee abonnee, IAbonnement abonnement);

    void createAbonnement( IAbonnement abonnement );

    void shareAbonnement(IAbonnee abonnee, IAbonnee delendeAbonnee, IDienst dienst);

    AbonnementSoort getEnumSoort(String soort);

    AbonnementStatus getEnumStatus(String status);
}