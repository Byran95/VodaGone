package main.java.DomainApplication;

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
