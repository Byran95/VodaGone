package DomainApplication.MySQLDataAccess;

import DomainApplication.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Bryan van Elden on 14-10-16.
 */
public class AbonneeDAOMySQL extends MySQLDataAccessObject implements IAbonneeAccess {

    @Override
    public List<IAbonnee> getAllAbonnees() {
        MySQLDatabaseHelper helper = getDatabaseHelper();

        ResultSet resultSet = null;

        try {
            resultSet = helper.executeQuery("SELECT * FROM abonnee");
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }

        helper.close();
        List<IAbonnee> convertedList = convertResultSet( resultSet );

        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return convertedList;
    }

    private List<IAbonnee> convertResultSet( ResultSet resultSet ) {
        List<IAbonnee> results = new ArrayList<>();

        try {
            while (resultSet.next()) {
                IAbonnee abonnee = new Abonnee(
                        resultSet.getString("naam"),
                        resultSet.getString("achternaam"),
                        resultSet.getString("emailadres"),
                        resultSet.getInt("abonneeId")
                );
                results.add(abonnee);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IAbonnee findAbonneeMetEmail(String email) {
        MySQLDatabaseHelper helper = getDatabaseHelper();
        PreparedStatement ps;
        IAbonnee abonnee;

        try {
            ps = helper.getConnection().prepareStatement("SELECT * FROM abonnee WHERE emailadres = ?");
            ps.setString(1, email);
            abonnee = convertResultSet(helper.executeQuery(ps)).get(0);
            return abonnee;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createAbonnee(String naam, String achternaam, String emailadres){
        MySQLDatabaseHelper helper = getDatabaseHelper();
        PreparedStatement ps;

        try {
            ps = helper.getConnection().prepareStatement("INSERT INTO abonnee (emailadres, naam, achternaam)VALUES (?, ?, ?)");
            ps.setString(1, emailadres);
            ps.setString(2, naam);
            ps.setString(3, achternaam);
            helper.executeQuery(ps);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
    }
}
