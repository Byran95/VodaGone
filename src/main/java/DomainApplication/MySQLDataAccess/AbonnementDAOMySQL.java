package DomainApplication.MySQLDataAccess;

import DomainApplication.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        resultSet.getBoolean("verdubbeld"),
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
                        resultSet.getBoolean("verdubbelbaar"),
                        resultSet.getBoolean("deelbaar"))
                );
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

    private AbonnementSoort getEnumSoort(String soort) {
        switch (soort) {
            case "MAAND":
                return AbonnementSoort.MAAND;
            case "HALFJAAR":
                return AbonnementSoort.HALFJAAR;
            default:
                return AbonnementSoort.JAAR;
        }
    }

    private AbonnementStatus getEnumStatus(String status) {
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
