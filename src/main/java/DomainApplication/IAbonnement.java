package DomainApplication;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonnement {

    String naam = "Swag";

    void verdubbel();

    void deelMet( IAbonnee abonnee );

    void verleng( AbonnementSoort periode );

    void zegOp();

    float berekendMaandPrijs();

    int getAbonneeId();

    String getStartDatum();

    boolean getVerdubbeld();

    ArrayList getGedeeldMet();

    AbonnementSoort getSoort();

    AbonnementStatus getStatus();

    IDienst getDienst();

    void setDienst(IDienst dienst);
}