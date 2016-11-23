import oose.dea.domain.SettingsReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anders Egberts on 19/10/2016.
 */
public class SettingsReaderTest {

    @Test
    public void testSetAndGetString() {
        String expectedString = "TestValue";
        final String PROPERTY_NAME = "TestProperty";

        SettingsReader.setPropertyString( PROPERTY_NAME , expectedString );
        assertEquals( expectedString , SettingsReader.getPropertyString( PROPERTY_NAME , "DefaultValue" ) );
    }

    @Test
    public void testGetStringNotFound() {
        String expectedString = "DefaultValue";
        final String PROPERTY_NAME = "NonExistingProperty";

        assertEquals( expectedString , SettingsReader.getPropertyString( PROPERTY_NAME , "DefaultValue" ) );
    }

    @Test
    public void testSetMultiple() {
        String expectedString = "Value1";
        String secondValue = "Value2";
        final String PROPERTY_NAME1 = "Property1";
        final String PROPERTY_NAME2 = "Property2";

        SettingsReader.setPropertyString( PROPERTY_NAME1 , expectedString );
        SettingsReader.setPropertyString( PROPERTY_NAME2 , secondValue );

        assertEquals( expectedString , SettingsReader.getPropertyString( PROPERTY_NAME1 , "NotFound") );
    }
}
