package RESTService;

import DomainApplication.*;
import com.google.inject.Guice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anders Egberts on 20/10/2016.
 */
@WebServlet(
    value = "/tryService"
)
public class ProbeerDienstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAbonnee loggedInUser = (IAbonnee) req.getSession().getAttribute( "loggedInUser" );
        if ( null == loggedInUser ) {
            req.getRequestDispatcher( "/login.jsp" ).forward( req , resp );
            return;
        }

        String companyName = req.getParameter( "cn" );
        String serviceName = req.getParameter( "sn" );
        IDienst serviceToTryout = Guice.createInjector().getInstance( IDienstService.class ).getServiceByCompanyAndName( companyName , serviceName );

        int userId = loggedInUser.getAbonneeId();

        IAbonnement abonnement = new Abonnement( userId , "" , false , AbonnementSoort.MAAND , AbonnementStatus.PROEF );
        abonnement.setDienst( serviceToTryout );

        Guice.createInjector().getInstance( IAbonnementService.class ).createAbonnement( abonnement );

        req.getRequestDispatcher("/abonnementen").forward( req, resp);
    }
}
