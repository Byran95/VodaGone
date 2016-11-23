package Vodagone.DomainApplication;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonnee {

    String getAchternaam();

    String getNaam();

    String getEmailadres();

    int getAbonneeId();

    void setAchternaam(String achternaam);

    void setNaam(String naam);

    void setEmailadres(String emailadres);

    void setAbonneeId(int abonneeId);
}
