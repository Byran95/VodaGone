package main.java.DomainApplication;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonnee {

    public String getAchternaam();

    public String getNaam();

    public String getEmailadres();

    public int getAbonneeId();

    public void setAchternaam(String achternaam);

    public void setNaam(String naam);

    public void setEmailadres(String emailadres);

    public void setAbonneeId(int abonneeId);
}
