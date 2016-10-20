package DomainApplication;

import java.util.List;

/**
 * Created by Anders Egberts on 12/10/2016.
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

    /**
     * Attemps to share an abonnement
     * @param abonnee the recipient of the abonnement
     * @param delendeAbonnee the owner of the abonnement
     * @param dienst the service that provided with the abonnement.
     * @return true if successful
     */
    boolean shareAbonnement(IAbonnee abonnee, IAbonnee delendeAbonnee, IDienst dienst);

    AbonnementSoort getEnumSoort(String soort);

    AbonnementStatus getEnumStatus(String status);
}