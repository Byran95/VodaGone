package oose.dea.services.rest;

import com.google.inject.Inject;
import oose.dea.domain.IDienst;
import oose.dea.domain.IDienstAccess;
import oose.dea.services.IDienstService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Anders Egberts on 25/11/2016.
 */
@Path("/dienst")
public class DienstRestService implements IDienstService {

    @Inject
    private IDienstAccess dienstAccess;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IDienst> getAll() {
        return dienstAccess.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{searchterm}")
    public List<IDienst> search(@PathParam("searchterm") String searchTerm) {
        System.out.println("jersey searchTerm: " + searchTerm);
        return dienstAccess.search(searchTerm);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{companyName}/{serviceName}")
    public IDienst getServiceByCompanyAndName(@PathParam("companyName") String companyName, @PathParam("serviceName") String serviceName) {
        System.out.println("companyName: " + companyName);
        System.out.println("serviceName: " + serviceName);
        return dienstAccess.getDienstByCompanyAndName(companyName, serviceName);
    }
}
