package jersey;

import DomainApplication.IDienst;
import DomainApplication.dienst.DienstDAOFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Bryan on 23-11-2016.
 */
@Path("/dienst")
public class DienstJerseyService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IDienst> getAll() {
        return DienstDAOFactory.getAccessObject().getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{searchterm}")
    public List<IDienst> search(@PathParam("searchterm") String searchTerm) {
        System.out.println("jersey searchTerm: " + searchTerm);
        return DienstDAOFactory.getAccessObject().search(searchTerm);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{companyName}/{serviceName}")
    public IDienst getServiceByCompanyAndName(@PathParam("companyName") String companyName, @PathParam("serviceName") String serviceName) {
        System.out.println("companyName: " + companyName);
        System.out.println("serviceName: " + serviceName);
        return DienstDAOFactory.getAccessObject().getDienstByCompanyAndName(companyName, serviceName);
    }
}
