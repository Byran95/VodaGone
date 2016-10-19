package DomainApplication;

import DomainApplication.IAbonnementAccess;
import DomainApplication.MySQLDataAccess.AbonnementDAOMySQL;

/**
 * Created by Anders Egberts on 18/10/2016.
 */
public class AbonnementDAOFactory {

    public static IAbonnementAccess getAccessObject() {
        switch ( SettingsReader.getPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "MySQL" ) ) {
            case "MySQL":
                return new AbonnementDAOMySQL();
        }
        return null;
    }
}
