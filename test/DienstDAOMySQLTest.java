import TestUtil.MockedResultSet;
import main.java.DomainApplication.IDienst;
import main.java.DomainApplication.MySQLDataAccess.DienstDAOMySQL;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertNotNull;
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

    private class DienstResultSet extends MockedResultSet {

        private DienstResultSet() {
            maxResults = 1;
        }
        @Override
        public String getString(String columnLabel) throws SQLException {
            switch( columnLabel ) {
                case "naam":
                    switch ( rowIndex ) {
                        case 1:
                            return "Ultra cool abonnement";
                        default:
                            return "Gaafe dienst";
                    }
                case "beschrijving":
                    switch ( rowIndex ) {
                        case 1:
                            return "Dit ding is ultra cool";
                        default:
                            return "Deze dienst is gaaf";
                    }
                default:
                    switch ( rowIndex ) {
                        case 1:
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
                        case 1:
                            return false;
                        default:
                            return true;
                    }
                default:
                    switch ( rowIndex ) {
                        case 1:
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
                        case 1:
                            return 50;
                        default:
                            return 40;
                    }
                case "jaarPrijs":
                    switch ( rowIndex ) {
                        case 1:
                            return 100;
                        default:
                            return 80;
                    }
                default:
                    switch ( rowIndex ) {
                        case 1:
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
    public void testGetAll() {
        List<IDienst> results = dienstDAOMySQL.getAll();
        assertNotNull( results );
    }
}
