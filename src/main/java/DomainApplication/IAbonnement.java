package DomainApplication;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonnement {

    void verdubbel();

    void deelMet( IAbonnee abonnee );

    void verleng( AbonnementSoort periode );

    void zegOp();

    float getMaandPrijs();

    int getAbonneeId();

    String getStartDatum();

    boolean getVerdubbeld();

    ArrayList getGedeeldMet();

    AbonnementSoort getSoort();

    AbonnementStatus getStatus();

    IDienst getDienst();

    void setAbonneeId(int abonneeId);

    void setStartDatum(String startDatum);

    void setVerdubbeld(String verdubbeld);

    void setGedeeldMet(String gedeeldMet);

    void setSoort(int soort);

    void setStatus(int status);

    void setDienst(IDienst dienst);
}