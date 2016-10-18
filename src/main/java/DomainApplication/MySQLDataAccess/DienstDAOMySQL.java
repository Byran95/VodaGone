package main.java.DomainApplication.MySQLDataAccess;

import main.java.DomainApplication.Dienst;
import main.java.DomainApplication.IDienst;
import main.java.DomainApplication.IDienstAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public class DienstDAOMySQL extends MySQLDataAccessObject implements IDienstAccess {
    @Override
    public List<IDienst> getAll() {
        MySQLDatabaseHelper helper = getDatabaseHelper();

        ResultSet resultSet = null;
        try {
            resultSet = helper.executeQuery("SELECT * FROM dienst");
            System.out.println( "Query executed" );
            System.out.println( "preferredConnection: " + preferredConnection );
            System.out.println( "helper: " + helper );
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
        try {
            List<IDienst> results = new ArrayList<>();
            System.out.println( "resultSet: " + resultSet );

            while (resultSet.next()) {
                Dienst dienst = new Dienst(
                        resultSet.getString("bedrijf"),
                        resultSet.getString("naam"),
                        resultSet.getString("beschrijving"),
                        resultSet.getInt("maandPrijs"),
                        resultSet.getInt("halfJaarPrijs"),
                        resultSet.getInt("jaarPrijs"),
                        resultSet.getBoolean("verdubbelbaar"),
                        resultSet.getBoolean("deelbaar")
                );
                results.add(dienst);
            }

            helper.close();

            return results;
        } catch (SQLException e) {
            helper.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(IDienst dienst) {
        return false;
    }

    @Override
    public boolean update(IDienst dienst) {
        return false;
    }
}
