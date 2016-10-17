import main.java.DomainApplication.Dienst;
import main.java.DomainApplication.IDienst;
import main.java.DomainApplication.MySQLDataAccess.DienstDAOMySQL;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Anders Egberts on 17/10/2016.
 */
public class DienstDAOMySQLTest {
    private static DienstDAOMySQL dienstDAOMySQL = new DienstDAOMySQL();

    @Test
    public void testGetAll() {
        List<IDienst> results = dienstDAOMySQL.getAll();
        assertNotNull( results );
    }
}
