package DomainApplication.abonnee;

import DomainApplication.IAbonnee;
import DomainApplication.abonnee.AbonneeService;
import DomainApplication.abonnement.AbonnementService;
import jersey.AbonneeJerseyService;
import jersey.AbonnementJerseyService;

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
        urlPatterns = { "/register" }
)
public class registerAbonnee extends HttpServlet {
    AbonneeJerseyService jerseyService = new AbonneeJerseyService();
    AbonneeService service = new AbonneeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ( null == req.getSession().getAttribute( "loggedInUser" ) ) {
            String userEmail = req.getParameter( "email" );
            String firstName = req.getParameter( "firstName" );
            String lastName = req.getParameter( "lastName" );

            //Empty fields will be reset to null for convenience.
            if ( "".equals( userEmail ) ) {
                userEmail = null;
            }
            if ( "".equals( firstName ) ) {
                firstName = null;
            }
            if ( "".equals( lastName ) ) {
                lastName = null;
            }

            if ( null == userEmail || null == lastName || null == firstName ) {
                if ( !( null == userEmail && null == lastName && null == firstName ) ) {
                    if ( null == userEmail ) {
                        req.setAttribute("errorMsg", "Vul een e-mailadres in!");
                    }
                    if ( null == lastName ) {
                        req.setAttribute("errorMsg", "Vul een achternaam in!");
                    }
                    if ( null == firstName ) {
                        req.setAttribute("errorMsg", "Vul een voornaam in!");
                    }
                }
                req.getRequestDispatcher("/createAccount.jsp").forward(req, resp);
            } else if ( null != userEmail && null != lastName && null != firstName ) {
                //Succes
                Abonnee abonnee = new Abonnee(firstName, lastName, userEmail);
                jerseyService.createAbonnee( abonnee );
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } else {
            resp.getWriter().write( "You are already logged in!");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost( req , resp );
    }
}