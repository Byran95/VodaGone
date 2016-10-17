package main.java.DomainApplication;

import java.util.List;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public interface IDienstAccess {

    List<IDienst> getAll();
    boolean create( IDienst );
    boolean update( IDienst );

}
