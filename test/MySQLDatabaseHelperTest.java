import Util.MySQLDatabaseHelper;
import Util.NoDatabaseConnectionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Anders Egberts on 17/10/2016.
 */
public class MySQLDatabaseHelperTest {
    private MySQLDatabaseHelper dbHelper = null;

    @Before
    public void setup() {
        dbHelper = new MySQLDatabaseHelper();
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

    @After
    public void close() {
        dbHelper.close();
    }
}
