package Vodagone.DomainApplication;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public class Dienst implements IDienst {

    private String bedrijf;
    private String naam;
    private String beschrijving;
    private int maandPrijs;
    private int halfJaarPrijs;
    private int jaarPrijs;
    private boolean verdubbelbaar;
    private boolean deelbaar;

    public Dienst(String bedrijf, String naam, String beschrijving, int maandPrijs, int halfJaarPrijs, int jaarPrijs, boolean verdubbelbaar, boolean deelbaar) {
        this.bedrijf = bedrijf;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.maandPrijs = maandPrijs;
        this.halfJaarPrijs = halfJaarPrijs;
        this.jaarPrijs = jaarPrijs;
        this.verdubbelbaar = verdubbelbaar;
        this.deelbaar = deelbaar;
    }

    public String getBedrijf() {
        return bedrijf;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getMaandPrijs() {
        return maandPrijs;
    }

    public int getHalfJaarPrijs() {
        return halfJaarPrijs;
    }

    public int getJaarPrijs() {
        return jaarPrijs;
    }

    public boolean isVerdubbelbaar() {
        return verdubbelbaar;
    }

    public boolean isDeelbaar() {
        return deelbaar;
    }
}
