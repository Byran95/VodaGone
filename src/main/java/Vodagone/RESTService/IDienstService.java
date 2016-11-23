package Vodagone.RESTService;

import Vodagone.DomainApplication.IDienst;

import java.util.List;

/**
 * Created by Anders Egberts on 22/11/2016.
 */
public interface IDienstService {
    List<IDienst> getAll();
    List<IDienst> search( String searchTerm );
    IDienst getServiceByCompanyAndName( String companyName , String serviceName );
}