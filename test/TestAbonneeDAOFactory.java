import DomainApplication.abonnee.AbonneeDAOFactory;
import DomainApplication.abonnee.AbonneeDAOMySQL;
import Util.SettingsReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Anders Egberts on 19/10/2016.
 */
public class TestAbonneeDAOFactory {

    @Test
    public void testGetMySQL() {
        SettingsReader.setPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "MySQL" );
        assertEquals(AbonneeDAOMySQL.class , AbonneeDAOFactory.getAccessObject().getClass() );
    }

    @Test
    public void testGetNoSQL() {
        SettingsReader.setPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "NoSQL" );
        //assertEquals(AbonneeDAONoSQL.class , AbonneeDAOFactory.getAccessObject().getClass() );
    }

    @Test
    public void testNotFound() {
        SettingsReader.setPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "Nothing" );
        assertNull( AbonneeDAOFactory.getAccessObject() );
    }
}
