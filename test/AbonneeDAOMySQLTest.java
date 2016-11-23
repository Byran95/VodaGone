import Vodagone.DomainApplication.Abonnee;
import Vodagone.DomainApplication.IAbonnee;
import Vodagone.DomainApplication.MySQLDataAccess.AbonneeDAOMySQL;
import TestUtil.MockedResultSet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Michel Koolwaaij on 10-10-16.
 */
public class AbonneeDAOMySQLTest {

    private static AbonneeDAOMySQL abonneeDAO = new AbonneeDAOMySQL();

    @Mock
    private Connection mockedConnection;
    @Mock
    private Statement mockedStatement;
    @Mock
    private PreparedStatement mockedPreparedStatement;
    @Mock
    private DriverManager mockDriverManager;

    private class AbonneeResultSet extends MockedResultSet {

        private AbonneeResultSet() {
            maxResults = 2;
        }

        @Override
        public String getString(String columnLabel) throws SQLException {
            switch( columnLabel ) {
                case "naam":
                    switch ( rowIndex ) {
                        case 1:
                            return "Sjaak";
                        default:
                            return "Hans";
                    }
                case "achternaam":
                    switch ( rowIndex ) {
                        case 1:
                            return "van de Berg";
                        default:
                            return "Teeuwen";
                    }
                default:
                    switch ( rowIndex ) {
                        case 1:
                            return "sjaak.vdberg@live.nl";
                        default:
                            return "Hans@Teeuwen@hotmail.nl";
                    }
            }
        }

        @Override
        public int getInt(String columnLabel) throws SQLException {
            switch( columnLabel ) {
                default:
                    switch ( rowIndex ) {
                        case 1:
                            return 1;
                        default:
                            return 2;
                    }
            }
        }
    }

    @Before
    public void globalSetup() throws SQLException {
        MockitoAnnotations.initMocks(this);

        when( mockedConnection.createStatement() ).thenReturn( mockedStatement );
        when( mockedConnection.prepareStatement( anyString() ) ).thenReturn( mockedPreparedStatement );
        when( mockedPreparedStatement.executeQuery() ).thenReturn( new AbonneeResultSet() );

        abonneeDAO.setConnection( mockedConnection );
    }

    @Test
    public void testGet() throws Exception {
        List<IAbonnee> abonneeList = abonneeDAO.getAllAbonnees();
        assertNotNull(abonneeList);
    }

    @Test
    public void testVoornaam() throws Exception {
        String actualVoornaam = abonneeDAO.getAllAbonnees().get(0).getNaam();
        String expectedVoornaam = "Sjaak";
        assertEquals(expectedVoornaam, actualVoornaam);
    }

    @Test
    public void testAchternaam() throws Exception {
        String expectedAchternaam = "Teeuwen";
        String actualAchternaam = abonneeDAO.getAllAbonnees().get(1).getAchternaam();
        assertEquals(expectedAchternaam, actualAchternaam);
    }

    @Test
    public void testEmailadres() throws Exception {
        String expectedEmailadres = "sjaak.vdberg@live.nl";
        String actualEmailadres = abonneeDAO.getAllAbonnees().get(0).getEmailadres();
        assertEquals(expectedEmailadres, actualEmailadres);
    }

    @Test
    public void testFindAbonnee() throws Exception {
        int expectedAbonneeId = 1;
        int actualAbonneeId = abonneeDAO.findAbonneeMetEmail("sjaak.vdberg@live.nl").getAbonneeId();
        assertEquals(expectedAbonneeId, actualAbonneeId);
    }

    @Test
    public void testInsertAbonnee() throws Exception {
        abonneeDAO.createAbonnee("Hans", "Teeuwen", "Hans@Teeuwen@hotmail.nl");
        IAbonnee expectedValue = abonneeDAO.findAbonneeMetEmail("Hans@Teeuwen@hotmail.nl");
        assertNotNull(expectedValue);
    }
}