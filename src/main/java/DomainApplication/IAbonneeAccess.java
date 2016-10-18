package DomainApplication;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IAbonneeAccess {

    public String getNaam(int id) throws SQLException;

    public String getAchternaam(int id) throws SQLException;

    public String getEmailadres(int id) throws SQLException;

    public IAbonnee findAbonnee(int id);

    public IAbonnee findAbonneeMetEmail(String email);

    public void createAbonnee(String naam, String achternaam, String emailadres);

    public List<IAbonnee> makeAbonneeList();

}
