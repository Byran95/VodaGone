package main.java.DomainApplication.MySQLDataAccess;

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
        try {
            connection = DriverManager.getConnection( DATABASE_URL , USER , PASS );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery( String statement ) {
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
}
