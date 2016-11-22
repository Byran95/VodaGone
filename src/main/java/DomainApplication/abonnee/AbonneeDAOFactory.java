package DomainApplication.abonnee;

import Util.SettingsReader;

/**
 * Created by Bryan van Elden on 18/10/2016.
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
