import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Anders Egberts on 18/10/2016.
 */
public class SettingsReader {
    private static final String PROPERTIES_FILENAME = "settings.xml";

    //These variables will help developers find some important properties.
    public static final String DATABASE_TYPE_PROPERTY = "DatabaseType";

    /**
     * Gets a property from the properties file. When the file or property can't be found return the defaultValue.
     * @param propertyName  The key of the property to return.
     * @param defaultValue  This will be returned if no file or property was found.
     * @return the value of the property on succes or the defaultValue on error.
     */
    public static String getPropertyString( String propertyName , String defaultValue ) {
        Properties loadProperties = new Properties();
        try {
            loadProperties.loadFromXML( new FileInputStream( PROPERTIES_FILENAME ));
            String foundValue = loadProperties.getProperty( propertyName );
            if ( null == foundValue ) {
                return defaultValue;
            }
            return loadProperties.getProperty( propertyName );
        } catch (IOException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Set a property with given name.
     * @param propertyName  The key of the property to set.
     * @param value         The new value of the property.
     */
    public static void setPropertyString( String propertyName , String value ) {
        Properties properties = new Properties();
        try {
            properties.loadFromXML( new FileInputStream( PROPERTIES_FILENAME ) );
            properties.setProperty( propertyName , value );
            properties.storeToXML( new FileOutputStream( PROPERTIES_FILENAME ) , null );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
