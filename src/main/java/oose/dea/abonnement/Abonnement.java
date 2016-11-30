package oose.dea.abonnement;

import oose.dea.abonnee.IAbonnee;
import oose.dea.dienst.IDienst;

import java.util.ArrayList;
import java.util.List;

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

    public Abonnement(int abonnementId, String startDatum, boolean verdubbeld, AbonnementSoort soort, AbonnementStatus status) {
        this.abonnementId = abonnementId;
        this.startDatum = startDatum;
        this.verdubbeld = verdubbeld;
        this.soort = soort;
        this.status = status;
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
    public float getMaandPrijs() {
        return 0;
    }

    @Override
    public int getAbonneeId() {
        return this.abonnementId;
    }

    //    Getters and Setters
    @Override
    public String getStartDatum() {
        return this.startDatum;
    }

    @Override
    public boolean getVerdubbeld() {
        return this.verdubbeld;
    }

    @Override
    public List<IAbonnee> getGedeeldMet() {
        return new ArrayList();
    }

    @Override
    public boolean isDeelbaar() {
        return true;
    }

    @Override
    public AbonnementSoort getSoort() {
        return this.soort;
    }

    @Override
    public AbonnementStatus getStatus() {
        return this.status;
    }

    @Override
    public IDienst getDienst() {
        return this.dienst;
    }

    @Override
    public void setAbonneeId(int abonneeId) {

    }

    @Override
    public void setStartDatum(String startDatum) {

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
    public void setDienst(IDienst dienst) {
        this.dienst = dienst;
    }
}
