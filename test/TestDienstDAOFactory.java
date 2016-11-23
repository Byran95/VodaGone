import Vodagone.DomainApplication.DienstDAOFactory;
import Vodagone.DomainApplication.MySQLDataAccess.DienstDAOMySQL;
import Vodagone.DomainApplication.SettingsReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Anders Egberts on 19/10/2016.
 */
public class TestDienstDAOFactory {
    @Test
    public void testGetMySQL() {
        SettingsReader.setPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "MySQL" );
        assertEquals(DienstDAOMySQL.class , DienstDAOFactory.getAccessObject().getClass() );
    }

    @Test
    public void testGetNoSQL() {
        SettingsReader.setPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "NoSQL" );
        //assertEquals(DienstDAONoSQL.class , DienstDAOFactory.getAccessObject().getClass() );
    }

    @Test
    public void testNotFound() {
        SettingsReader.setPropertyString( SettingsReader.DATABASE_TYPE_PROPERTY , "Nothing" );
        assertNull( DienstDAOFactory.getAccessObject() );
    }
}
