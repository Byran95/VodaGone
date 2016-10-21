package RESTService;

import DomainApplication.IAbonnee;

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
public class RegisterAbonnee extends HttpServlet {
    private AbonneeService abonneeService = new AbonneeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ( null == req.getSession().getAttribute( "loggedInUser" ) ) {
            String userEmail = req.getParameter( "email" );
            String firstName = req.getParameter( "firstName" );
            String lastNameName = req.getParameter( "lastName" );

            //Empty fields will be reset to null for convenience.
            if ( "".equals( userEmail ) ) {
                userEmail = null;
            }
            if ( "".equals( firstName ) ) {
                firstName = null;
            }
            if ( "".equals( lastNameName ) ) {
                lastNameName = null;
            }

            if ( null == userEmail || null == lastNameName || null == firstName ) {
                if ( !( null == userEmail && null == lastNameName && null == firstName ) ) {
                    if ( null == userEmail ) {
                        req.setAttribute("errorMsg", "Vul een e-mailadres in!");
                    }
                    if ( null == lastNameName ) {
                        req.setAttribute("errorMsg", "Vul een achternaam in!");
                    }
                    if ( null == firstName ) {
                        req.setAttribute("errorMsg", "Vul een voornaam in!");
                    }
                }
                req.getRequestDispatcher("/createAccount.jsp").forward(req, resp);
            } else if ( null != userEmail && null != lastNameName && null != firstName ) {
                //Succes
                new AbonneeService().createAbonnee( firstName , lastNameName , userEmail );
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