package DomainApplication.MySQLDataAccess;

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
     * Creates the object. On creation the object will attempt to connect to the db.
     */
    public MySQLDatabaseHelper() {
//        ServerLogger.log( getClass() , "No custom connection" );
        try {
            DriverManager.registerDriver( new org.mariadb.jdbc.Driver() );
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);
//            ServerLogger.log( getClass() , "connection: " + connection );
        } catch (SQLException e) {
//            ServerLogger.log( getClass() , "Failed to setup connection" );
            e.printStackTrace();
        }
    }

    /**
     * @param preferredConnection Specify a connection to create a custom connection (for testing purposes).
     *                            Leave empty to use default MySQL connection.
     * Creates the object. On creation the object will attemp to connect to the db.
     */
    public MySQLDatabaseHelper( Connection preferredConnection ) {
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

    /**
     * This version of the executequery function lets the developer pass an array of string that serve as parameters for
     * the statement.
     * @param statement Base statement
     * @param statementParams statementParameters as a string array.
     * @return a ResultSet containing the results of the query.
     * @throws NoDatabaseConnectionException when no viable connection is set.
     */
    public ResultSet executeQuery( String statement , String[] statementParams ) throws NoDatabaseConnectionException {
        if ( null == connection ) {
            throw new NoDatabaseConnectionException();
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement( statement );
            int index = 0;
            for ( String statementParam : statementParams ) {
                preparedStatement.setString( index , statementParam );
                index += 1;
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * executes a query based on a prepared statement.
     * @param preparedStatement
     * @return a ResultSet containing the results of the query.
     * @throws NoDatabaseConnectionException
     */
    public ResultSet executeQuery( PreparedStatement preparedStatement ) throws NoDatabaseConnectionException {
        if ( null == connection ) {
            throw new NoDatabaseConnectionException();
        }

        try {
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

    public Connection getConnection() {
        return this.connection;
    }
}
