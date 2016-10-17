package main.java.DomainApplication.MySQLDataAccess;

import main.java.DomainApplication.IDienst;
import main.java.DomainApplication.IDienstAccess;

import java.util.List;

/**
 * Created by Anders Egberts on 12/10/2016.
 */
public class DienstDAOMySQL implements IDienstAccess {
    @Override
    public List<IDienst> getAll() {
        return null;
    }

    @Override
    public boolean create() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }
}
