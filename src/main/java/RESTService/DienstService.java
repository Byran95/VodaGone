package RESTService;

import DomainApplication.DienstDAOFactory;
import DomainApplication.IDienst;

import java.util.List;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
public class DienstService {
    public List<IDienst> getAll() {
        return DienstDAOFactory.getAccessObject().getAll();
    }
}
