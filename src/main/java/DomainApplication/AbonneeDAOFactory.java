package DomainApplication;

import DomainApplication.IAbonneeAccess;
import DomainApplication.MySQLDataAccess.AbonneeDAOMySQL;

/**
 * Created by Anders Egberts on 18/10/2016.
 */
public class AbonneeDAOFactory {

    public static IAbonneeAccess getAccessObject() {
        switch ( SettingsReader.getPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "MySQL" ) ) {
            case "MySQL":
                return new AbonneeDAOMySQL();
        }
        return null;
    }
}
