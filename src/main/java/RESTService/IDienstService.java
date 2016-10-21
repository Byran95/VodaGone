package RESTService;

import DomainApplication.IDienst;
import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * Created by Anders Egberts on 21/10/2016.
 */
@ImplementedBy(DienstService.class)
public interface IDienstService {
    List<IDienst> getAll();
    List<IDienst> search( String searchTerm );
    IDienst getServiceByCompanyAndName( String companyName , String serviceName );
}
