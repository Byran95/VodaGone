import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import main.java.DomainApplication.IAbonnee;
import main.java.DomainApplication.Abonnee.AbonneeDAOMySQL;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Michel Koolwaaij on 10-10-16.
 */
public class AbonneeClientTest {

    private static AbonneeDAOMySQL dao = new AbonneeDAOMySQL();
    private static List<IAbonnee> abonneeList = dao.makeAbonneeList();

    @Before
    public void before() {
        for (IAbonnee abonnee : abonneeList){
            System.out.println(abonnee);
        }
    }

    @Test
    public void testVoornaamEersteAbonnee() throws Exception {
        String expectedVoornaam = "Sjaak";
        String actualVoornaam = abonneeList.get(0).getNaam();
        assertEquals(expectedVoornaam, actualVoornaam);
    }

    @Test
    public void testAchternaamEersteAbonnee() throws Exception {
        String expectedAchternaam = "van de Berg";
        String actualAchternaam = abonneeList.get(0).getAchternaam();
        assertEquals(expectedAchternaam, actualAchternaam);
    }

    @Test
    public void testEmailEersteAbonnee() throws Exception {
        String expectedEmailadres = "sjaak.vdberg@live.nl";
        String actualEmailadres = abonneeList.get(0).getEmailadres();
        assertEquals(expectedEmailadres, actualEmailadres);
    }

    @After
    public void after() throws SQLException {
        dao.closeConnection();
        System.out.println("connection closed");
    }
}