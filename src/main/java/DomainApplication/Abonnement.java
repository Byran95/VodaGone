package DomainApplication;

import java.util.ArrayList;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public class Abonnement implements IAbonnement {
    private int abonnementId;
    private String startDatum;
    private boolean verdubbeld;
    private ArrayList<IAbonnee> gedeeldMet = new ArrayList<>();
    private AbonnementSoort soort;
    private AbonnementStatus status;
    private IDienst dienst;

    public Abonnement(int abonnementId, String startDatum, boolean verdubbeld, AbonnementSoort soort, AbonnementStatus status ){
        this.abonnementId=abonnementId;
        this.startDatum=startDatum;
        this.verdubbeld = verdubbeld;
        this.soort=soort;
        this.status=status;
    }

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

    @Override
    public int getAbonneeId() {
        return 0;
    }

    //    Getters and Setters
    @Override
    public java.util.Date getStartDatum() {
        return null;
    }

    @Override
    public boolean getVerdubbeld() {
        return false;
    }

    @Override
    public ArrayList getGedeeldMet() {
        return null;
    }

    @Override
    public AbonnementSoort getSoort() {
        return null;
    }

    @Override
    public AbonnementStatus getStatus() {
        return null;
    }

    @Override
    public IDienst getDienst() {
        return null;
    }

    @Override
    public void setAbonneeId(java.util.Date abonneeId) {

    }

    @Override
    public void setStartDatum(java.util.Date startDatum) {

    }

    @Override
    public void setVerdubbeld(String verdubbeld) {

    }

    @Override
    public void setGedeeldMet(String gedeeldMet) {

    }

    @Override
    public void setSoort(int soort) {

    }

    @Override
    public void setStatus(int status) {

    }

    @Override
    public void setDienst(int dienst) {

    }
}
