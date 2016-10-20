package RESTService;

import DomainApplication.IAbonnee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
@WebServlet(
        urlPatterns = { "/cancelSubscription" }
)
public class DienstOpzeggenServlet extends HttpServlet {
    AbonnementService abonnementService = new AbonnementService();
    AbonneeService abonneeService = new AbonneeService();

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

        abonnementService.cancelSubscription(abonneeId, bedrijf, naam);
        req.getRequestDispatcher( "/abonnementen" ).forward( req , resp );
    }
}