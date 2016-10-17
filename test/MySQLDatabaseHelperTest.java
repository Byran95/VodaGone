import main.java.DomainApplication.MySQLDataAccess.MySQLDatabaseHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        ResultSet resultSet = dbHelper.executeQuery( "SELECT * FROM Dienst" );
        assertNotNull( resultSet );
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        dbHelper.close();
    }
}
