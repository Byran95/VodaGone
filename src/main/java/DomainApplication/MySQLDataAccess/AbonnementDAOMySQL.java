package DomainApplication.MySQLDataAccess;

import DomainApplication.*;
import Util.ServerLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan van Elden on 12/10/2016.
 */
public class AbonnementDAOMySQL extends MySQLDataAccessObject implements IAbonnementAccess {

    @Override
    public List<IAbonnement> getAllAbonnementen() {
        MySQLDatabaseHelper helper = getDatabaseHelper();

        ResultSet resultSet = null;
        try {
            resultSet = helper.executeQuery("SELECT dienst.*, abonnement.abonneeId, abonnement.abonnementStatus, abonnement.abonnementSoort, abonnement.startDatum, abonnement.verdubbeld FROM abonnement INNER JOIN dienst ON abonnement.bedrijf = dienst.bedrijf AND abonnement.naam = dienst.naam");
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
        helper.close();
        ServerLogger.log( getClass() , "resultSet: " + resultSet );
        List<IAbonnement> convertedList = convertResultSet( resultSet );
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return convertedList;
    }

    private List<IAbonnement> convertResultSet( ResultSet resultSet ) {
        List<IAbonnement> results = new ArrayList<>();


        try {
            while (resultSet.next()) {
                IAbonnement abonnement = new Abonnement(
                resultSet.getInt("abonneeId"),
                        resultSet.getString("startDatum"),
                        intToBoolean(resultSet.getInt("verdubbeld")),
                        getEnumSoort(resultSet.getString("abonnementSoort")),
                        getEnumStatus(resultSet.getString("abonnementStatus"))
                );
                abonnement.setDienst(new Dienst(
                        resultSet.getString("bedrijf"),
                        resultSet.getString("naam"),
                        resultSet.getString("beschrijving"),
                        resultSet.getInt("maandPrijs"),
                        resultSet.getInt("halfJaarPrijs"),
                        resultSet.getInt("jaarPrijs"),
                        intToBoolean(resultSet.getInt("verdubbelbaar")),
                        intToBoolean(resultSet.getInt("deelbaar"))
                ));
                System.out.println( "ab.verdubbeld: " + intToBoolean(resultSet.getInt("verdubbeld")) );
                results.add(abonnement);
            }
            System.out.println("Size: " + results.size());
            System.out.println("First record: : " + results);
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IAbonnement> findAbonnementenVanAbonnee(int id){
        MySQLDatabaseHelper helper = getDatabaseHelper();
        PreparedStatement ps;
        List<IAbonnement> mijnAbonnementen;

        try {
            ps = helper.getConnection().prepareStatement("SELECT * FROM abonnement INNER JOIN dienst ON abonnement.bedrijf = dienst.bedrijf AND abonnement.naam = dienst.naam WHERE abonnement.abonneeId = ?");
            ps.setInt(1, id);
            mijnAbonnementen = convertResultSet(helper.executeQuery(ps));
            return mijnAbonnementen;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateAbonnementSoort(AbonnementSoort soort, IAbonnee abonnee, IDienst dienst) {
        MySQLDatabaseHelper helper = getDatabaseHelper();
        PreparedStatement ps;

        try {
            ps = helper.getConnection().prepareStatement("UPDATE abonnement SET abonnementSoort=? WHERE abonneeId = ? AND bedrijf = ? AND naam = ?");
            ps.setString(1, soort.toString());
            ps.setInt(2, abonnee.getAbonneeId());
            ps.setString(3, dienst.getBedrijf());
            ps.setString(4, dienst.getNaam());
            helper.executeQuery(ps);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAbonnementStatus(AbonnementStatus status, IAbonnee abonnee, IDienst dienst) {
        MySQLDatabaseHelper helper = getDatabaseHelper();
        PreparedStatement ps;

        try {
            ps = helper.getConnection().prepareStatement("UPDATE abonnement SET abonnementStatus=? WHERE abonneeId = ? AND bedrijf = ? AND naam = ?");
            ps.setString(1, status.toString());
            ps.setInt(2, abonnee.getAbonneeId());
            ps.setString(3, dienst.getBedrijf());
            ps.setString(4, dienst.getNaam());
            helper.executeQuery(ps);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAbonnement(IAbonnement abonnement) {
        MySQLDatabaseHelper helper = getDatabaseHelper();
        PreparedStatement ps;

        int verdubbeld = booleanToInt(abonnement.getVerdubbeld());

        try {
            ps = helper.getConnection().prepareStatement("INSERT INTO table_name (abonneeId, bedrijf, naam, abonnementStatus, abonnementSoort, startDatum, verdubbeld)\n" +
                    "VALUES (?, ? ,?, ?, ?, ?, ?);");
            ps.setInt(1, abonnement.getAbonneeId());
            ps.setString(1, abonnement.getDienst().getBedrijf());
            ps.setString(1, abonnement.getDienst().getNaam());
            ps.setString(1, abonnement.getStatus().toString());
            ps.setString(1, abonnement.getSoort().toString());
            ps.setString(1, abonnement.getStartDatum());
            ps.setInt(1, verdubbeld);
            helper.executeQuery(ps);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateIsVerdubbeld(boolean verdubbeld, int abonneeId, String bedrijf, String naam) {
        MySQLDatabaseHelper helper = getDatabaseHelper();
        PreparedStatement ps;
        int verdubbeldBit = booleanToInt(verdubbeld);

        System.out.println("UPDATE abonnement SET verdubbeld=" + verdubbeld + " WHERE abonneeId = " + abonneeId + " AND bedrijf = " + bedrijf + " AND naam = " + naam + "");

        try {
            ps = helper.getConnection().prepareStatement("UPDATE abonnement SET verdubbeld=? WHERE abonneeId = ? AND bedrijf = ? AND naam = ?");
            ps.setInt(1, verdubbeldBit);
            ps.setInt(2, abonneeId);
            ps.setString(3, bedrijf);
            ps.setString(4, naam);
            helper.executeQuery(ps);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAbonnementDelenToegestaan(IAbonnee abonnee, IAbonnement abonnement) {
        MySQLDatabaseHelper helper = getDatabaseHelper();
        PreparedStatement ps;
        int nGedeeld = 0;
        ResultSet rs = null;

        try {
            ps = helper.getConnection().prepareStatement(
                    "SELECT COUNT(*) AS nGedeeld \n" +
                            "FROM abonnement \n" +
                            "INNER JOIN gedeeldeabonnementen \n" +
                            "\tON gedeeldeabonnementen.abonneeId = abonnement.abonneeId\n" +
                            "    AND gedeeldeabonnementen.bedrijf = abonnement.bedrijf\n" +
                            "    AND gedeeldeabonnementen.naam = abonnement.naam\n" +
                            "WHERE abonnement.abonneeId = ? " +
                            "AND abonnement.bedrijf = ? " +
                            "AND abonnement.naam = ?");
            ps.setInt(1, abonnee.getAbonneeId());
            ps.setString(2, abonnement.getDienst().getBedrijf());
            ps.setString(3, abonnement.getDienst().getNaam());

            rs = helper.executeQuery(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                nGedeeld = rs.getInt("nGedeeld");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(nGedeeld < 2) {
            return true;
        }
        System.out.println("nGedeeld: " + nGedeeld);
        return false;
    }

    @Override
    public void shareAbonnement(IAbonnee abonnee, IAbonnee delendeAbonnee, IDienst dienst) {

    }

    public boolean intToBoolean(int bit) {
        if(bit == 1) {
            return true;
        }
        return false;
    }

    public int booleanToInt(Boolean bool) {
        if(bool == true) {
            return 1;
        }
        return 0;
    }

    @Override
    public AbonnementSoort getEnumSoort(String soort) {
        switch (soort) {
            case "MAAND":
                return AbonnementSoort.MAAND;
            case "HALFJAAR":
                return AbonnementSoort.HALFJAAR;
            default:
                return AbonnementSoort.JAAR;
        }
    }

    @Override
    public AbonnementStatus getEnumStatus(String status) {
        switch (status) {
            case "OPGEZEGD":
                return AbonnementStatus.OPGEZEGD;
            case "PROEF":
                return AbonnementStatus.PROEF;
            default:
                return AbonnementStatus.ACTIEF;
        }
    }
}
