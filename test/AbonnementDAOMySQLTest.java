import DomainApplication.*;
import DomainApplication.MySQLDataAccess.AbonneeDAOMySQL;
import DomainApplication.MySQLDataAccess.AbonnementDAOMySQL;
import DomainApplication.MySQLDataAccess.DienstDAOMySQL;
import TestUtil.MockedResultSet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.Enumeration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Bryan on 17-10-2016.
 */
public class AbonnementDAOMySQLTest {
    private static AbonnementDAOMySQL abonnementDAO = new AbonnementDAOMySQL();

    @Mock
    private Connection mockedConnection;
    @Mock
    private Statement mockedStatement;
    @Mock
    private PreparedStatement mockedPreparedStatement;
    @Mock
    private DriverManager mockDriverManager;

    private class AbonnementResultSet extends MockedResultSet {

        private AbonnementResultSet() {
            maxResults = 2;
        }

        @Override
        public String getString(String columnLabel) throws SQLException {
            switch( columnLabel ) {
                case "bedrijf":
                    switch ( rowIndex ) {
                        case 2:
                            return "Vodafone";
                        default:
                            return "Ziggo";
                    }
                case "naam":
                    switch ( rowIndex ) {
                        case 2:
                            return "Mobiel 100";
                        default:
                            return "Mobiel 250";
                    }
                case "beschrijving":
                    switch ( rowIndex ) {
                        case 2:
                            return "Mobiele telefonie met 100 minuten, SMS of GB";
                        default:
                            return "Mobiele telefonie met 250 minuten, SMS of GB";
                    }
                case "abonnementSoort":
                    switch ( rowIndex ) {
                        case 2:
                            return "MAAND";
                        default:
                            return "JAAR";
                    }
                case "abonnementStatus":
                    switch ( rowIndex ) {
                        case 2:
                            return "OPGEZEGD";
                        default:
                            return "ACTIEF";
                    }
                case "startDatum":
                    switch ( rowIndex ) {
                        case 2:
                            return "2016-10-18 10:42:36";
                        default:
                            return "2016-10-17 11:44:00";
                    }
                default:
                    switch ( rowIndex ) {
                        case 2:
                            return "Vodafone";
                        default:
                            return "Ziggo";
                    }
            }
        }

        @Override
        public boolean getBoolean(String columnLabel) throws SQLException {
            switch( columnLabel ) {
                case "deelbaar":
                    switch ( rowIndex ) {
                        case 2:
                            return false;
                        default:
                            return true;
                    }
                case "verdubbeld":
                    switch ( rowIndex ) {
                        case 2:
                            return false;
                        default:
                            return true;
                    }
                default:
                    switch ( rowIndex ) {
                        case 2:
                            return false;
                        default:
                            return true;
                    }
            }
        }

        @Override
        public int getInt(String columnLabel) throws SQLException {
            switch( columnLabel ) {
                case "halfJaarPrijs":
                    switch ( rowIndex ) {
                        case 2:
                            return 25;
                        default:
                            return 40;
                    }
                case "abonneeId":
                    switch ( rowIndex ) {
                        case 2:
                            return 1;
                        default:
                            return 2;
                    }
                case "jaarPrijs":
                    switch ( rowIndex ) {
                        case 2:
                            return 45;
                        default:
                            return 80;
                    }
                default:
                    switch ( rowIndex ) {
                        case 2:
                            return 5;
                        default:
                            return 20;
                    }
            }
        }
    }

    @Before
    public void globalSetup() throws SQLException {
        MockitoAnnotations.initMocks(this);

        when( mockedConnection.createStatement() ).thenReturn( mockedStatement );
        when( mockedConnection.prepareStatement( anyString() ) ).thenReturn( mockedPreparedStatement );
        when( mockedPreparedStatement.executeQuery() ).thenReturn( new AbonnementResultSet() );

        abonnementDAO.setConnection( mockedConnection );
    }

    private List<IAbonnement> abonnementList;
    IAbonnement expectedAbonnement = new Abonnement(
            1,
            "2016-10-18 10:42:36",
            false,
            AbonnementSoort.MAAND,
            AbonnementStatus.OPGEZEGD
    );

    @Test
    public void testGet() throws Exception {
        abonnementList = abonnementDAO.getAllAbonnementen();
        assertNotNull(abonnementList);
    }

    @Test
    public void testAbonnee() throws Exception {
        abonnementList = abonnementDAO.getAllAbonnementen();
        assertEquals(expectedAbonnement.getAbonneeId(), abonnementList.get(1).getAbonneeId());
        assertEquals(expectedAbonnement.getStartDatum(), abonnementList.get(1).getStartDatum());
        assertEquals(expectedAbonnement.getVerdubbeld(), abonnementList.get(1).getVerdubbeld());
        assertEquals(expectedAbonnement.getSoort(), abonnementList.get(1).getSoort());
        assertEquals(expectedAbonnement.getStatus(), abonnementList.get(1).getStatus());
    }

    @Test
    public void testAbonneeMetDienst() throws Exception {
        abonnementList = abonnementDAO.getAllAbonnementen();
        expectedAbonnement.setDienst(new Dienst(
                "Vodafone",
                "Mobiel 100",
                "Mobiele telefonie met 100 minuten, SMS of GB",
                5,
                25,
                45,
                false,
                false
        ));
        assertEquals(expectedAbonnement.getDienst(), abonnementList.get(0).getDienst());
    }

    @Test
    public void testUpdateAbonnementSoortEnStatus() throws Exception {
        IAbonnee abonnee = new Abonnee(
                "Sjaak",
                "van de Berg",
                "sjaak.vdberg@live.nl",
                1
        );

       IDienst dienst = new Dienst(
                "Vodafone",
                "Mobiel 100",
                "Mobiele telefonie met 100 minuten, SMS of GB",
                5,
                25,
                45,
                false,
                false
        );
//        AbonnementDAOMySQL dao = new AbonnementDAOMySQL();
//        dao.updateAbonnementSoort(AbonnementSoort.JAAR, abonnee, dienst);
//        dao.getUpdateAbonnementStatus(AbonnementStatus.PROEF, abonnee, dienst);
    }

    @Test
    public void testUpdateIsVerdubbeld() throws Exception {
        IAbonnee abonnee = new Abonnee(
                "Sjaak",
                "van de Berg",
                "sjaak.vdberg@live.nl",
                1
        );

        IDienst dienst = new Dienst(
                "Vodafone",
                "Mobiel 100",
                "Mobiele telefonie met 100 minuten, SMS of GB",
                5,
                25,
                45,
                false,
                false
        );
//        AbonnementDAOMySQL dao = new AbonnementDAOMySQL();
//        dao.updateIsVerdubbeld(true, abonnee, dienst);
    }
}