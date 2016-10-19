import DomainApplication.MySQLDataAccess.AbonnementDAOMySQL;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Anders Egberts on 19/10/2016.
 */
public class TestAbonnementDAOFactory {
    @Test
    public void testGetMySQL() {
        SettingsReader.setPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "MySQL" );
        assertEquals(AbonnementDAOMySQL.class , AbonnementDAOFactory.getAccessObject().getClass() );
    }

    @Test
    public void testGetNoSQL() {
        SettingsReader.setPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "NoSQL" );
        //assertEquals(AbonnementDAONoSQL.class , AbonnementDAOFactory.getAccessObject().getClass() );
    }

    @Test
    public void testNotFound() {
        SettingsReader.setPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "Nothing" );
        assertNull( AbonnementDAOFactory.getAccessObject() );
    }
}
