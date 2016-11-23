package oose.dea.domain;

import java.util.List;

/**
 * Contains a few functions to easily manipulate the database with.
 * This interface needs to be implemented by a class for each databasetype (e.g.: MySQL, NoSQL, Flat files)
 * (Eis: IM1)
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonneeAccess {

    List<IAbonnee> getAllAbonnees();

    /**
     * Returns all abonnees that are sharing someone else's abonnement
     * @param sharedAbonnement The abonnement to find the free-loaders for.
     * @return a list containing all sharers.
     */
    List<IAbonnee> getAbonneesThatAreSharingAbonnement( IAbonnement sharedAbonnement );

    /**
     * Finds an abonnee by e-mail.
     * @param email the e-mail to find the abonnee by.
     * @return the IAbonee that was found or null on error.
     */
    IAbonnee findAbonneeMetEmail(String email);

    /**
     * Searches the DB to find an abonnee with a given abonneeId
     * @param findId the index to find the abonnee by.
     * @return the IAbonee that was found or null on error.
     */
    IAbonnee findAbonneeById( int  findId );

    /**
     * Creates a new abonee entry in the db.
     * @param naam          The first name of the new abonnee
     * @param achternaam    The last name of the new abonnee
     * @param emailadres    The e-mailadress of the new abonnee.
     */
    void createAbonnee(String naam, String achternaam, String emailadres);
}
