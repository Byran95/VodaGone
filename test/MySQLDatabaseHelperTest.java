import Vodagone.DomainApplication.MySQLDataAccess.MySQLDatabaseHelper;
import Vodagone.DomainApplication.MySQLDataAccess.NoDatabaseConnectionException;
import TestUtil.MockedResultSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Anders Egberts on 17/10/2016.
 */
public class MySQLDatabaseHelperTest {
    private MySQLDatabaseHelper dbHelper = null;

    @Mock
    private Connection mockedConnection;
    @Mock
    private Statement mockedStatement;
    @Mock
    private PreparedStatement mockedPreparedStatement;
    @Mock
    private DriverManager mockDriverManager;

    @Before
    public void setup() throws SQLException {
        dbHelper = new MySQLDatabaseHelper();
        MockitoAnnotations.initMocks(this);

        when( mockedConnection.createStatement() ).thenReturn( mockedStatement );
        when( mockedConnection.prepareStatement( anyString() ) ).thenReturn( mockedPreparedStatement );
        when( mockedPreparedStatement.executeQuery() ).thenReturn( new MockedResultSet() );

        dbHelper.setConnection( mockedConnection );
    }
    @Test
    public void testQuery() {
        ResultSet resultSet = null;
        try {
            resultSet = dbHelper.executeQuery( "SELECT * FROM Dienst" );
        } catch (NoDatabaseConnectionException e) {
            e.printStackTrace();
        }
        assertNotNull( resultSet );
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NoDatabaseConnectionException.class)
    public void testNoConnection() throws NoDatabaseConnectionException {
        dbHelper.setConnection( null );
        dbHelper.executeQuery( "" );
    }

    @Test
    public void testSQLBroken() throws SQLException, NoDatabaseConnectionException {
        doThrow( new SQLException() ).when( mockedPreparedStatement ).execute( anyString() );
        doThrow( new SQLException() ).when( mockedConnection ).prepareStatement( anyString() );
        assertNull( dbHelper.executeQuery( "SELECT * FROM Dienst" ) );
    }

    @After
    public void close() {
        dbHelper.close();
    }
}
