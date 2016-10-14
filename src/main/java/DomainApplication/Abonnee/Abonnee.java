package DomainApplication.Abonnee;

import DomainApplication.IAbonnee;
import DomainApplication.IAbonnement;
import java.util.ArrayList;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public class Abonnee implements IAbonnee {

    private String naam;
    private String emailadres;
    private ArrayList<IAbonnement> mijnAbonnementen;
}
