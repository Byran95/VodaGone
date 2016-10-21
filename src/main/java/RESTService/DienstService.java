package RESTService;

import DomainApplication.DienstDAOFactory;
import DomainApplication.IDienst;

import java.util.List;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
public class DienstService {
    /*
    Directe aanroep methoden voor communicatie in de container (Eis IO1)
     */
    public List<IDienst> getAll() {
        return DienstDAOFactory.getAccessObject().getAll();
    }
    public List<IDienst> search( String searchTerm ) {
        return DienstDAOFactory.getAccessObject().search( searchTerm );
    }
    public IDienst getServiceByCompanyAndName( String companyName , String serviceName ) {
        return DienstDAOFactory.getAccessObject().getDienstByCompanyAndName( companyName , serviceName );
    }
}
