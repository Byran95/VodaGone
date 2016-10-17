package main.java.DomainApplication;

import main.java.DomainApplication.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public class Abonnement implements IAbonnement {
    private Date startDatum;
    private boolean verdubbeld;
    private ArrayList<IAbonnee> gedeeldMet = new ArrayList<>();
    private AbonnementSoort soort;
    private AbonnementStatus status;
    private IDienst hoortBijDienst;

    @Override
    public void verdubbel() {

    }

    @Override
    public void deelMet(IAbonnee abonnee) {

    }

    @Override
    public void verleng(AbonnementSoort periode) {

    }

    @Override
    public void zegOp() {

    }

    @Override
    public float berekendMaandPrijs() {
        return 0;
    }
}
