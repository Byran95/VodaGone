package jersey;

import DomainApplication.IAbonnee;
import DomainApplication.IAbonnement;
import DomainApplication.abonnee.AbonneeDAOFactory;
import DomainApplication.abonnement.AbonnementDAOFactory;
import DomainApplication.abonnement.AbonnementStatus;
import DomainApplication.abonnement.IAbonnementAccess;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Bryan on 22-11-2016.
 */
@Path("abonnement")
public class AbonnementJerseyService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public List<IAbonnement> getAbonnementenForUser(@PathParam("id") int abonneeId) {
        System.out.println("abonneeId: " + abonneeId);
        return AbonnementDAOFactory.getAccessObject().findAbonnementenVanAbonnee(abonneeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/{company}/{name}")
    public IAbonnement getByOwnerCompanyandName(@PathParam("id") int abonneeId, @PathParam("company") String company, @PathParam("name") String name ) {
        return AbonnementDAOFactory.getAccessObject().findAbonnement(abonneeId, company, name);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/upgrade/{verdubbeld}/{id}/{company}/{name}")
    public void updateVerdubbeld(@PathParam("verdubbeld") boolean verdubbeld, @PathParam("id") int abonneeId, @PathParam("company") String bedrijf, @PathParam("name") String naam) {
        AbonnementDAOFactory.getAccessObject().updateIsVerdubbeld(verdubbeld, abonneeId, bedrijf, naam);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cancel/{id}/{company}/{name}")
    public void cancelSubscription(@PathParam("id") int abonneeId, @PathParam("company") String bedrijf, @PathParam("name") String naam) {
        IAbonnement abonnement = AbonnementDAOFactory.getAccessObject().findAbonnement(abonneeId, bedrijf, naam);
        AbonnementDAOFactory.getAccessObject().updateAbonnementStatus(AbonnementStatus.OPGEZEGD, abonnement);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public void createAbonnement(IAbonnement abonnement) {
        System.out.println("jersey.Abonnee saved : " + abonnement.getAbonneeId() + ", " + abonnement.getDienst().getNaam());
        AbonnementDAOFactory.getAccessObject().createAbonnement(abonnement);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/share")
    public void shareWith( IAbonnement abonnementToShare, IAbonnee recepient ) {
        IAbonnee delendeAbonnee = AbonneeDAOFactory.getAccessObject().findAbonneeById( abonnementToShare.getAbonneeId() );
        AbonnementDAOFactory.getAccessObject().shareAbonnement( recepient , delendeAbonnee , abonnementToShare.getDienst() );
    }
}
