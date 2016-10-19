import DomainApplication.IDienstAccess;
import DomainApplication.MySQLDataAccess.DienstDAOMySQL;

/**
 * Created by Anders Egberts on 18/10/2016.
 */
public class DienstDAOFactory {

    public static IDienstAccess getAccessObject() {
        switch ( SettingsReader.getPropertyString( "DatabaseType" , "MySQL" ) ) {
            case "MySQL":
                return new DienstDAOMySQL();
        }
        return null;
    }
}
