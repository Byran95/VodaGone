package main.java.DomainApplication;

import main.java.DomainApplication.IDienst;

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
}
