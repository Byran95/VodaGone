package oose.dea.abonnement;

import com.google.inject.Inject;
import oose.dea.abonnee.IAbonnee;
import oose.dea.abonnee.IAbonneeAccess;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Anders Egberts on 25/11/2016.
 */
@Path("abonnement")
public class AbonnementRestService implements IAbonnementService {

    @Inject
    private IAbonnementAccess dataAccessObject;
    @Inject
    private IAbonneeAccess abonneeAccess;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public List<IAbonnement> getAbonnementenForUser(@PathParam("id") int abonneeId) {
        System.out.println("abonneeId: " + abonneeId);
        return dataAccessObject.findAbonnementenVanAbonnee(abonneeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/{company}/{name}")
    public IAbonnement getByOwnerCompanyandName(@PathParam("id") int abonneeId, @PathParam("company") String company, @PathParam("name") String name ) {
        return dataAccessObject.findAbonnement(abonneeId, company, name);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/upgrade/{verdubbeld}/{id}/{company}/{name}")
    public void updateVerdubbeld(@PathParam("verdubbeld") boolean verdubbeld, @PathParam("id") int abonneeId, @PathParam("company") String bedrijf, @PathParam("name") String naam) {
        dataAccessObject.updateIsVerdubbeld(verdubbeld, abonneeId, bedrijf, naam);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cancel/{id}/{company}/{name}")
    public void cancelSubscription(@PathParam("id") int abonneeId, @PathParam("company") String bedrijf, @PathParam("name") String naam) {
        IAbonnement abonnement = dataAccessObject.findAbonnement(abonneeId, bedrijf, naam);
        dataAccessObject.updateAbonnementStatus(AbonnementStatus.OPGEZEGD, abonnement);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    public List<IAbonnement> getAll() {
        return dataAccessObject.getAllAbonnementen();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getFor/{id}")
    public List<IAbonnement> getByAbonnee(@PathParam("id")int abonneeId) {
        return getAbonnementenForUser( abonneeId );
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public void createAbonnement(IAbonnement abonnement) {
        System.out.println("jersey.Abonnee saved : " + abonnement.getAbonneeId() + ", " + abonnement.getDienst().getNaam());
        dataAccessObject.createAbonnement(abonnement);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/share")
    public boolean shareWith( IAbonnement abonnementToShare, IAbonnee recepient ) {
        IAbonnee delendeAbonnee = abonneeAccess.findAbonneeById( abonnementToShare.getAbonneeId() );
        return dataAccessObject.shareAbonnement( recepient , delendeAbonnee , abonnementToShare.getDienst() );
    }
}
