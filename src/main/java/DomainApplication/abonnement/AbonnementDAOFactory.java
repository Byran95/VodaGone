package DomainApplication.abonnement;

import Util.SettingsReader;

/**
 * Created by Bryan van Elden on 18/10/2016.
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
