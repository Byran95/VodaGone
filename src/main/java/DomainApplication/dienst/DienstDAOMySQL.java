package DomainApplication.dienst;

import Util.MySQLDataAccessObject;
import Util.MySQLDatabaseHelper;
import Util.NoDatabaseConnectionException;
import DomainApplication.IDienst;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan van Elden on 12/10/2016.
 */
public class DienstDAOMySQL extends MySQLDataAccessObject implements IDienstAccess {
    @Override
    public List<IDienst> getAll() {
        MySQLDatabaseHelper helper = getDatabaseHelper();

        ResultSet resultSet = null;
        try {
            resultSet = helper.executeQuery("SELECT * FROM dienst");
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
        helper.close();
        List<IDienst> convertedList = convertResultSet( resultSet );
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return convertedList;
    }

    /**
     * Searches the diensts in the database that match the search term.
     *
     * @param searchTerm this word mut either be in the title, companyname or discription of the servlets.
     * @return a list containing all matching services.
     */
    @Override
    public List<IDienst> search(String searchTerm) {
        MySQLDatabaseHelper helper = getDatabaseHelper();

        ResultSet resultSet = null;

        try {
            resultSet = helper.executeQuery( "SELECT * FROM dienst WHERE naam LIKE ?" , new String[]{"%" + searchTerm + "%"});
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
        helper.close();
        List<IDienst> convertedList = convertResultSet( resultSet );
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return convertedList;
    }

    /**
     * Searches the db for a servlets matching the companyName and serviceName (These params together for a unique key).
     *
     * @param companyName The company that provides the servlets.
     * @param serviceName The name of the servlets.
     * @return a IDienst matching the search-params if succesful or null on error.
     */
    @Override
    public IDienst getDienstByCompanyAndName(String companyName, String serviceName) {
        MySQLDatabaseHelper helper = getDatabaseHelper();

        ResultSet resultSet = null;

        try {
            resultSet = helper.executeQuery( "SELECT * FROM dienst WHERE bedrijf = ? AND naam = ?" , new String[]{companyName , serviceName});
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
        helper.close();
        List<IDienst> convertedList = convertResultSet( resultSet );
        if ( 0 < convertedList.size() ) {
            return  convertedList.get( 0 );
        }

        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<IDienst> convertResultSet( ResultSet resultSet ) {
        List<IDienst> results = new ArrayList<>();

        if ( null == resultSet ) {
            return results;
        }
        try {
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
            return results;
        } catch (SQLException e) {
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