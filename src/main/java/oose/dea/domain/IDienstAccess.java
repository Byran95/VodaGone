package oose.dea.domain;

import java.util.List;

/**
 * Contains a few functions to easily manipulate the database with.
 * This interface needs to be implemented by a class for each databasetype (e.g.: MySQL, NoSQL, Flat files)
 * (Eis: IM1)
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
     * @param searchTerm this word mut either be in the title, companyname or discription of the service.
     * @return a list containing all matching services.
     */
    List<IDienst> search( String searchTerm );

    /**
     * Searches the db for a service matching the companyName and serviceName (These params together for a unique key).
     * @param companyName   The company that provides the service.
     * @param serviceName   The name of the service.
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
