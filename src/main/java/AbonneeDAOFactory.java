import DomainApplication.IAbonneeAccess;
import DomainApplication.MySQLDataAccess.AbonneeDAOMySQL;

/**
 * Created by Anders Egberts on 18/10/2016.
 */
public class AbonneeDAOFactory {

    public static IAbonneeAccess getAccessObject() {
        switch ( SettingsReader.getPropertyString( "DatabaseType" ) ) {
            case "MySQL":
                return new AbonneeDAOMySQL();
        }
        return null;
    }
}
