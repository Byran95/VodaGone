package oose.dea.dienst;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IDienst {
    String getBedrijf();
    String getNaam();
    String getBeschrijving();
    int getMaandPrijs();
    int getHalfJaarPrijs();
    int getJaarPrijs();
    boolean isVerdubbelbaar();
    boolean isDeelbaar();
}
