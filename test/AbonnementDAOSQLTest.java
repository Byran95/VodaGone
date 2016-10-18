import main.java.DomainApplication.IAbonnee;
import main.java.DomainApplication.IAbonnement;
import main.java.DomainApplication.MySQLDataAccess.AbonnementDAOMySQL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Bryan on 17-10-2016.
 */
public class AbonnementDAOSQLTest {

    private static AbonnementDAOMySQL dao = new AbonnementDAOMySQL();
    private static List<IAbonnement> abonnementList = dao.makeAbonnementList();
    private static List<IAbonnement> mijnAbonnementen;

    @Before
    public void before() {
        for (IAbonnement abonnement : abonnementList){
            System.out.println(abonnement);
        }
    }

    @Test
    public void testGet() throws Exception {
        assertNotNull(abonnementList);
    }

    @Test
    public void testAantalAbonnemenentAbonnee1() throws Exception {
        assertEquals(dao.findAbonnementenVanAbonnee(1).size(), 2);
    }

    @Test
    public void testAantalAbonnemenentAbonnee2() throws Exception {
        assertEquals(dao.findAbonnementenVanAbonnee(2).size(), 1);
    }

}
