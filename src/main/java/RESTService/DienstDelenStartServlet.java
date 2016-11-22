package RESTService;

import DomainApplication.IAbonnee;
import DomainApplication.IAbonnement;
import com.google.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Deze Servlet bevat geen markup-code (Eis: M2)
 *
 * Haalt de users op waarmee de dienst gedeeld kan worden.
 * Created by Anders Egberts on 20/10/2016.
 */
@WebServlet(
        value = "/startSharingService"
)
public class DienstDelenStartServlet extends HttpServlet {
    @Inject
    private IAbonnementService abonnementService;
    @Inject
    private IAbonneeService abonneeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAbonnee loggedInUser = (IAbonnee) req.getSession().getAttribute( "loggedInUser" );
        if ( null == loggedInUser ) {
            req.getRequestDispatcher( "/login.jsp" ).forward( req , resp );
            return;
        }

        int abonneeId = loggedInUser.getAbonneeId();
        String bedrijf = req.getParameter("bedrijf");
        String naam = req.getParameter("naam");

        if ( null == bedrijf || null == naam ) {
            return;
        }

        //Build a list that contains all the abonnees with who the abonnement can't shared (e.g.: users that are already sharing, the abonnementowner)
        IAbonnement abonnementToShare = abonnementService.getByOwnerCompanyandName( abonneeId , bedrijf , naam );
        List<IAbonnee> ignoreList = abonneeService.getAbonneesThatAreSharing( abonnementToShare );
        ignoreList.add( loggedInUser ); //Can't share with ourselves

        req.setAttribute( "abonnement" , abonnementToShare );
        req.setAttribute( "users" , abonneeService.getAllWithFilter( ignoreList ) );
        req.getRequestDispatcher( "/dienstDelen.jsp" ).forward( req , resp );
    }
}
