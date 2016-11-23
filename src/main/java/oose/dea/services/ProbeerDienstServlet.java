package oose.dea.services;

import com.google.inject.Inject;
import oose.dea.domain.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deze Servlet bevat geen markup-code (Eis: M2)
 *
 * Created by Anders Egberts on 20/10/2016.
 */
@WebServlet(
    value = "/tryService"
)
public class ProbeerDienstServlet extends HttpServlet {
    @Inject
    private IAbonnementService abonnementService;
    @Inject
    private IDienstService dienstService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAbonnee loggedInUser = (IAbonnee) req.getSession().getAttribute( "loggedInUser" );
        if ( null == loggedInUser ) {
            req.getRequestDispatcher( "/login.jsp" ).forward( req , resp );
            return;
        }

        String companyName = req.getParameter( "cn" );
        String serviceName = req.getParameter( "sn" );
        IDienst serviceToTryout = dienstService.getServiceByCompanyAndName( companyName , serviceName );

        int userId = loggedInUser.getAbonneeId();

        IAbonnement abonnement = new Abonnement( userId , "" , false , AbonnementSoort.MAAND , AbonnementStatus.PROEF );
        abonnement.setDienst( serviceToTryout );

        abonnementService.createAbonnement( abonnement );

        req.getRequestDispatcher("/abonnementen").forward( req, resp);
    }
}
