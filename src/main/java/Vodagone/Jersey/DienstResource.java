package Vodagone.Jersey;

import Vodagone.DomainApplication.IDienst;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders Egberts on 22/11/2016.
 */
public class DienstResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IDienst> getAll() {
        //TODO: Implement get
        return new ArrayList<>();
    }
}
