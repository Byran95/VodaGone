package main.java.DomainApplication.MySQLDataAccess;

import sun.rmi.runtime.Log;

import java.sql.*;

/**
 * Created by Anders Egberts on 17/10/2016.
 */
public class MySQLDatabaseHelper {
    private final String DATABASE_URL = "jdbc:mysql://localhost/vodagone";
    private final String USER = "root";
    private final String PASS = "";

    private Connection connection = null;

    /**
     * Creates the object. On creation the object will attemp to connect to the db.
     */
    public MySQLDatabaseHelper() {
        System.out.println( "No custom connection" );
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param preferredConnection Specify a connection to create a custom connection (for testing purposes).
     *                            Leave empty to use default MySQL connection.
     * Creates the object. On creation the object will attemp to connect to the db.
     */
    public MySQLDatabaseHelper( Connection preferredConnection ) {
        System.out.println( "With custom connection" );
        setConnection( preferredConnection );
    }

    public ResultSet executeQuery( String statement ) throws NoDatabaseConnectionException {

        if ( null == connection ) {
            throw new NoDatabaseConnectionException();
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement( statement );
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        if ( null != connection ) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Allows the programmer to set-up a custom connection (for mockpurposes).
     */
    public void setConnection( Connection newConnection ) {
        if ( null != connection ){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection = newConnection;
    }
}
