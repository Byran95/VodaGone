package DomainApplication;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonnement {

    void verdubbel();
    void deelMet( IAbonnee abonnee );
    void verleng( AbonnementSoort periode );
    void zegOp();
    float berekendMaandPrijs();
}