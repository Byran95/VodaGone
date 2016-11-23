package Vodagone.DomainApplication.MySQLDataAccess;

import java.sql.Connection;

/**
 * Created by Anders Egberts on 17/10/2016.
 */
public class MySQLDataAccessObject {
    protected Connection preferredConnection = null;

    public void setConnection( Connection newConnection ) {
        preferredConnection = newConnection;
    }

    protected MySQLDatabaseHelper getDatabaseHelper() {
        if ( null == preferredConnection ) {
            return new MySQLDatabaseHelper();
        } else {
            return new MySQLDatabaseHelper(preferredConnection);
        }
    }
}
