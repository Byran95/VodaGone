import TestUtil.MockedResultSet;
import main.java.DomainApplication.Dienst;
import main.java.DomainApplication.IDienst;
import main.java.DomainApplication.MySQLDataAccess.DienstDAOMySQL;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Anders Egberts on 17/10/2016.
 */
public class DienstDAOMySQLTest {
    private static DienstDAOMySQL dienstDAOMySQL = new DienstDAOMySQL();

    @Mock
    private Connection mockedConnection;
    @Mock
    private Statement mockedStatement;
    @Mock
    private PreparedStatement mockedPreparedStatement;
    @Mock
    private DriverManager mockDriverManager;

    private class DienstResultSet extends MockedResultSet {

        private DienstResultSet() {
            maxResults = 2;
        }

        @Override
        public String getString(String columnLabel) throws SQLException {
            switch( columnLabel ) {
                case "naam":
                    switch ( rowIndex ) {
                        case 2:
                            return "Ultra cool abonnement";
                        default:
                            return "Gaafe dienst";
                    }
                case "beschrijving":
                    switch ( rowIndex ) {
                        case 2:
                            return "Dit ding is ultra cool";
                        default:
                            return "Deze dienst is gaaf";
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
                default:
                    switch ( rowIndex ) {
                        case 2:
                            return true;
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
                            return 50;
                        default:
                            return 40;
                    }
                case "jaarPrijs":
                    switch ( rowIndex ) {
                        case 2:
                            return 100;
                        default:
                            return 80;
                    }
                default:
                    switch ( rowIndex ) {
                        case 2:
                            return 25;
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
        when( mockedPreparedStatement.executeQuery() ).thenReturn( new DienstResultSet() );

        dienstDAOMySQL.setConnection( mockedConnection );
    }

    @Test
    public void testGetAllNotNull() throws SQLException {
        List<IDienst> results = dienstDAOMySQL.getAll();
        assertNotNull( results );
    }

    @Test
    public void testGetAllCorrectLength() throws SQLException {
        List<IDienst> results = dienstDAOMySQL.getAll();
        assertEquals( 2 , results.size() );
    }

    @Test
    public void testGetAllMatchingResults() throws SQLException {
        IDienst ziggoDienst = new Dienst( "Ziggo" , "Gaafe dienst" , "Deze dienst is gaaf" ,  20 , 40 , 80 , true , true );
        IDienst vodafoneDienst = new Dienst( "Vodafone" , "Ultra cool abonnement" , "Dit ding is ultra cool" ,  25 , 50 , 100 , true , false );

        List<IDienst> results = dienstDAOMySQL.getAll();
        IDienst resultDienst = results.get( 0 );
        assertEquals( ziggoDienst.getBedrijf() ,  resultDienst.getBedrijf() );
        assertEquals( ziggoDienst.getNaam() ,  resultDienst.getNaam() );
        assertEquals( ziggoDienst.getBeschrijving() ,  resultDienst.getBeschrijving() );
        assertEquals( ziggoDienst.getHalfJaarPrijs() ,  resultDienst.getHalfJaarPrijs() );
        assertEquals( ziggoDienst.getJaarPrijs() ,  resultDienst.getJaarPrijs() );
        assertEquals( ziggoDienst.getMaandPrijs() ,  resultDienst.getMaandPrijs() );
        assertEquals( ziggoDienst.isVerdubbelbaar() ,  resultDienst.isVerdubbelbaar() );
        assertEquals( ziggoDienst.isDeelbaar() ,  resultDienst.isDeelbaar() );
        resultDienst = results.get( 1 );
        assertEquals( vodafoneDienst.getBedrijf() ,  resultDienst.getBedrijf() );
        assertEquals( vodafoneDienst.getNaam() ,  resultDienst.getNaam() );
        assertEquals( vodafoneDienst.getBeschrijving() ,  resultDienst.getBeschrijving() );
        assertEquals( vodafoneDienst.getHalfJaarPrijs() ,  resultDienst.getHalfJaarPrijs() );
        assertEquals( vodafoneDienst.getJaarPrijs() ,  resultDienst.getJaarPrijs() );
        assertEquals( vodafoneDienst.getMaandPrijs() ,  resultDienst.getMaandPrijs() );
        assertEquals( vodafoneDienst.isVerdubbelbaar() ,  resultDienst.isVerdubbelbaar() );
        assertEquals( vodafoneDienst.isDeelbaar() ,  resultDienst.isDeelbaar() );
    }

    @Test
    public void testSearchZiggoFullTerm() throws SQLException {
        IDienst ziggoDienst = new Dienst( "Ziggo" , "Gaafe dienst" , "Deze dienst is gaaf" ,  20 , 40 , 80 , true , true );
        IDienst vodafoneDienst = new Dienst( "Vodafone" , "Ultra cool abonnement" , "Dit ding is ultra cool" ,  25 , 50 , 100 , true , false );

        List<IDienst> results = dienstDAOMySQL.search( "Ziggo" );
        IDienst resultDienst = results.get( 0 );
        assertEquals( ziggoDienst.getBedrijf() ,  resultDienst.getBedrijf() );
        assertEquals( ziggoDienst.getNaam() ,  resultDienst.getNaam() );
        assertEquals( ziggoDienst.getBeschrijving() ,  resultDienst.getBeschrijving() );
        assertEquals( ziggoDienst.getHalfJaarPrijs() ,  resultDienst.getHalfJaarPrijs() );
        assertEquals( ziggoDienst.getJaarPrijs() ,  resultDienst.getJaarPrijs() );
        assertEquals( ziggoDienst.getMaandPrijs() ,  resultDienst.getMaandPrijs() );
        assertEquals( ziggoDienst.isVerdubbelbaar() ,  resultDienst.isVerdubbelbaar() );
        assertEquals( ziggoDienst.isDeelbaar() ,  resultDienst.isDeelbaar() );
    }

    @Test
    public void testSearchZiggoHalfTerm() throws SQLException {
        IDienst ziggoDienst = new Dienst( "Ziggo" , "Gaafe dienst" , "Deze dienst is gaaf" ,  20 , 40 , 80 , true , true );
        IDienst vodafoneDienst = new Dienst( "Vodafone" , "Ultra cool abonnement" , "Dit ding is ultra cool" ,  25 , 50 , 100 , true , false );

        List<IDienst> results = dienstDAOMySQL.search( "zig" );
        IDienst resultDienst = results.get( 0 );
        assertEquals( ziggoDienst.getBedrijf() ,  resultDienst.getBedrijf() );
        assertEquals( ziggoDienst.getNaam() ,  resultDienst.getNaam() );
        assertEquals( ziggoDienst.getBeschrijving() ,  resultDienst.getBeschrijving() );
        assertEquals( ziggoDienst.getHalfJaarPrijs() ,  resultDienst.getHalfJaarPrijs() );
        assertEquals( ziggoDienst.getJaarPrijs() ,  resultDienst.getJaarPrijs() );
        assertEquals( ziggoDienst.getMaandPrijs() ,  resultDienst.getMaandPrijs() );
        assertEquals( ziggoDienst.isVerdubbelbaar() ,  resultDienst.isVerdubbelbaar() );
        assertEquals( ziggoDienst.isDeelbaar() ,  resultDienst.isDeelbaar() );
    }

    @Test
    public void testSearchZiggoMultipleResults() throws SQLException {
        IDienst ziggoDienst = new Dienst( "Ziggo" , "Gaafe dienst" , "Deze dienst is gaaf" ,  20 , 40 , 80 , true , true );
        IDienst vodafoneDienst = new Dienst( "Vodafone" , "Ultra cool abonnement" , "Dit ding is ultra cool" ,  25 , 50 , 100 , true , false );

        List<IDienst> results = dienstDAOMySQL.search( "o" );
        assertEquals( 2 , results.size() );
    }
}
