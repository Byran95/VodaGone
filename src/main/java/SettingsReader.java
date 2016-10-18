import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Anders Egberts on 18/10/2016.
 */
public class SettingsReader {
    private static final String PROPERTIES_FILENAME = "settings.xml";

    public static String getPropertyString( String propertyName ) {
        Properties loadProperties = new Properties();
        try {
            loadProperties.loadFromXML( new FileInputStream( PROPERTIES_FILENAME ));
            return loadProperties.getProperty( propertyName );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
