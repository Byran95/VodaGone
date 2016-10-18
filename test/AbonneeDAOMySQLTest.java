import DomainApplication.IAbonnee;
import DomainApplication.MySQLDataAccess.AbonneeDAOMySQL;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Michel Koolwaaij on 10-10-16.
 */
public class AbonneeDAOMySQLTest {

    private static AbonneeDAOMySQL dao = new AbonneeDAOMySQL();
    private static List<IAbonnee> abonneeList = dao.getAllAbonnees();

    @Test
    public void testGet() throws Exception {
        assertNotNull(abonneeList);
        for (int i = 0; i < abonneeList.size(); i++) {
            System.out.println("Abonnee: " + abonneeList.get(i));
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

    @Test
    public void testAbonneeMetEmail() throws Exception {
        int expectedAbonneeId = 1;
        int actualAbonneeId = dao.findAbonneeMetEmail("sjaak.vdberg@live.nl").getAbonneeId();
        assertEquals(expectedAbonneeId, actualAbonneeId);
        System.out.println(dao.findAbonneeMetEmail("sjaak.vdberg@live.nl"));
    }

    @Test
    public void testInsertAbonnee() throws Exception {
        dao.createAbonnee("Hans", "Teeuwen", "Hans@Teeuwen@hotmail.nl");
        IAbonnee expectedValue = dao.findAbonneeMetEmail("Hans@Teeuwen@hotmail.nl");
        assertNotNull(expectedValue);
    }
}