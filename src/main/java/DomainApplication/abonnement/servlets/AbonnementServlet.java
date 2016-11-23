package DomainApplication.abonnement.servlets;

import DomainApplication.IAbonnee;
import DomainApplication.abonnement.AbonnementService;
import jersey.AbonnementJerseyService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Bryan van Elden on 14/10/2016.
 */
@WebServlet(
        urlPatterns = { "/abonnementen" }
)
public class AbonnementServlet extends HttpServlet {
//    AbonnementService service = new AbonnementService();
    AbonnementJerseyService jerseyService = new AbonnementJerseyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAbonnee loggedInUser = (IAbonnee) req.getSession().getAttribute( "loggedInUser" );
        if ( null == loggedInUser ) {
            req.getRequestDispatcher( "/login.jsp" ).forward( req , resp );
            return;
        }
        System.out.println( loggedInUser );
        req.setAttribute( "abonnementen" , jerseyService.getAbonnementenForUser( loggedInUser.getAbonneeId() ) );
        req.getRequestDispatcher( "/AbonnementView.jsp" ).forward( req , resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet( req , resp );
    }
}