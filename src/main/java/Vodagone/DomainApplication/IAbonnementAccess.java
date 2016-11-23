package Vodagone.DomainApplication;

import java.util.List;

/**
 * Contains a few functions to easily manipulate the database with.
 * This interface needs to be implemented by a class for each databasetype (e.g.: MySQL, NoSQL, Flat files)
 * (Eis: IM1)
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonnementAccess {

    /**
     * Find a unique abonnement by thee keys.
     * @param abonneeId     The owner of the abonnement (Used as a key)
     * @param bedrijf       The provider of the service associated with the abonnement (Used as a key)
     * @param naam          The naam of the service associated with the abonnement (Used as a key)
     * @return the abonnement that was found or null on error.
     */
    IAbonnement findAbonnement(int abonneeId, String bedrijf, String naam);

    List<IAbonnement> getAllAbonnementen();

    /**
     * Returns all abonnementen that are owned by a given abonnee.
     * @param id    The abonneeId of abonnee to find the abonnements for.
     * @return a List containing all found abonnements (empty if nothing was found.)
     */
    List<IAbonnement> findAbonnementenVanAbonnee(int id);

    void updateAbonnementSoort(AbonnementSoort soort, IAbonnement abonnement);

    /**
     * Changes the status of the abonnement in the database.
     * @param status        The new status of the abonnement
     * @param abonnement    The abonnement that was updated.
     */
    void updateAbonnementStatus(AbonnementStatus status, IAbonnement abonnement);

    /**
     * Upgrades a abonnement in the database.
     * @param isVerdubbeld  Determines whether the abonnement is upgraded.
     * @param abonneeId     The owner of the abonnement (Used as a key)
     * @param bedrijf       The provider of the service associated with the abonnement (Used as a key)
     * @param naam          The naam of the service associated with the abonnement (Used as a key)
     */
    void updateIsVerdubbeld(boolean isVerdubbeld, int abonneeId, String bedrijf, String naam);

    boolean isAbonnementDelenToegestaan(IAbonnee abonnee, IAbonnement abonnement);

    /**
     * Creates a new entry in the database containing the new abonnement-data.
     * @param abonnement    An instance of the abonnement that will be used to get the information to fill the record with.
     */
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