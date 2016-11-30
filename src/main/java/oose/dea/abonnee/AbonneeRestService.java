package oose.dea.abonnee;

import com.google.inject.Inject;
import oose.dea.abonnement.IAbonnement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders Egberts on 25/11/2016.
 */
@Path("/abonnee")
public class AbonneeRestService implements IAbonneeService {

    @Inject
    private IAbonneeAccess dataAccessObject;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IAbonnee> getAllAbonnees() {
        return dataAccessObject.getAllAbonnees();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public IAbonnee getAbonneeById(@PathParam("id") int abonneeId) {
        System.out.println("jersey abonneeId: " + abonneeId);
        return dataAccessObject.findAbonneeById(abonneeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("sharing/{id}")
    public List<IAbonnee> getAbonneesThatAreSharing( IAbonnement sharedAbonnement) {
        return dataAccessObject.getAbonneesThatAreSharingAbonnement( sharedAbonnement );
    }

    @Override
    public List<IAbonnee> getAllWithFilter(List<IAbonnee> filterAbonnees) {
        List<IAbonnee> abonnees = dataAccessObject.getAllAbonnees();
        List<IAbonnee> filteredList = new ArrayList<>();
        for( IAbonnee abonnee : abonnees ) {
            if ( !isInFilterList( abonnee , filterAbonnees ) ) {
                filteredList.add( abonnee );
            }
        }
        return filteredList;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("email/{email}")
    public IAbonnee getAbonneeByEmail(@PathParam("email") String email) {
        System.out.println("email: " + email);
        return dataAccessObject.findAbonneeMetEmail(email);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public void createAbonnee(IAbonnee abonnee) {
        System.out.println("jersey.Abonnee saved : " + abonnee.getEmailadres());
        dataAccessObject.createAbonnee(abonnee.getNaam(), abonnee.getAchternaam(), abonnee.getEmailadres());
    }

    private boolean isInFilterList( IAbonnee checkFor , List<IAbonnee> filterList ) {
        for( IAbonnee skipAbonee : filterList ) {
            if ( checkFor.getAbonneeId() == skipAbonee.getAbonneeId() ) {
                return true;
            }
        }
        return false;
    }
}
