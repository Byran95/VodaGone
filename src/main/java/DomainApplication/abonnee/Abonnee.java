package DomainApplication.abonnee;

import DomainApplication.IAbonnee;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Bryan van Elden on 14-10-16.
 */
@XmlRootElement
public class Abonnee implements IAbonnee {
    private String naam;
    private String achternaam;
    private String emailadres;
    private int abonneeId;

    public Abonnee(String naam, String achternaam, String emailadres){
        this.naam=naam;
        this.achternaam = achternaam;
        this.emailadres=emailadres;
    }

    public Abonnee(){}

    public Abonnee(String naam, String achternaam, String emailadres, int abonneeId){
        this.naam=naam;
        this.achternaam = achternaam;
        this.emailadres=emailadres;
        this.abonneeId=abonneeId;
    }

    @Override
    public String getAchternaam() {
        return achternaam;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public String getEmailadres() {
        return emailadres;
    }

    @Override
    public int getAbonneeId() {
        return abonneeId;
    }

    @Override
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    @Override
    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    @Override
    public void setAbonneeId(int abonneeId) {
        this.abonneeId = abonneeId;
    }
}
