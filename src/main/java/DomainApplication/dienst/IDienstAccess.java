package DomainApplication.dienst;

import DomainApplication.IDienst;

import java.util.List;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IDienstAccess {

    /**
     * Find all diensts and return them as a list.
     * @return a list containing all dienst.
     */
    List<IDienst> getAll();

    /**
     * Searches the diensts in the database that match the search term.
     * @param searchTerm this word mut either be in the title, companyname or discription of the servlets.
     * @return a list containing all matching services.
     */
    List<IDienst> search( String searchTerm );

    /**
     * Searches the db for a servlets matching the companyName and serviceName (These params together for a unique key).
     * @param companyName   The company that provides the servlets.
     * @param serviceName   The name of the servlets.
     * @return a IDienst matching the search-params if succesful or null on error.
     */
    IDienst getDienstByCompanyAndName( String companyName , String serviceName );

    /**
     * Create an entry in de persistance based on a given Dienst.
     * @param dienst the Dienst to base the entry on.
     * @return
     */
    boolean create( IDienst dienst );

    /**
     * Update the given dienst.
     * @param dienst the dienst to update.
     * @return
     */
    boolean update( IDienst dienst );

}