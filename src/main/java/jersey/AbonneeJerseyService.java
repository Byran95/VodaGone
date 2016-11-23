package jersey;

import DomainApplication.IAbonnee;
import DomainApplication.abonnee.Abonnee;
import DomainApplication.abonnee.AbonneeDAOFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Bryan on 22-11-2016.
 */
@Path("/abonnee")
public class AbonneeJerseyService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IAbonnee> getAllAbonnees() {
        return AbonneeDAOFactory.getAccessObject().getAllAbonnees();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public IAbonnee getAbonneeById(@PathParam("id") int abonneeId) {
        System.out.println("jersey abonneeId: " + abonneeId);
        return AbonneeDAOFactory.getAccessObject().findAbonneeById(abonneeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("email/{email}")
    public IAbonnee getAbonneeByEmail(@PathParam("email") String email) {
        System.out.println("email: " + email);
        return AbonneeDAOFactory.getAccessObject().findAbonneeMetEmail(email);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public void createAbonnee(Abonnee abonnee) {
        System.out.println("jersey.Abonnee saved : " + abonnee.getEmailadres());
        AbonneeDAOFactory.getAccessObject().createAbonnee(abonnee.getNaam(), abonnee.getAchternaam(), abonnee.getEmailadres());
    }

}
