import DomainApplication.IAbonnementAccess;
import DomainApplication.MySQLDataAccess.AbonnementDAOMySQL;

/**
 * Created by Anders Egberts on 18/10/2016.
 */
public class AbonnementDAOFactory {

    public static IAbonnementAccess getAccessObject() {
        switch ( SettingsReader.getPropertyString( "DatabaseType" ) ) {
            case "MySQL":
                return new AbonnementDAOMySQL();
        }
        return null;
    }
}
