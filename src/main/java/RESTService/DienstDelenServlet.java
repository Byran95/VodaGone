package RESTService;

import DomainApplication.IAbonnee;
import DomainApplication.IAbonnement;

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
    value = "/shareService"
)
public class DienstDelenServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAbonnee loggedInUser = (IAbonnee) req.getSession().getAttribute( "loggedInUser" );
        if ( null == loggedInUser ) {
            req.getRequestDispatcher( "/login.jsp" ).forward( req , resp );
            return;
        }

        int abonneeId = loggedInUser.getAbonneeId();
        String bedrijf = req.getParameter("bedrijf");
        String naam = req.getParameter("naam");
        int targetAbonneeId = Integer.parseInt( req.getParameter( "targetUserId" ) );

        if ( null == bedrijf || null == naam ) {
            return;
        }

        AbonnementService abonnementService = new AbonnementService();
        IAbonnement abonnementToShare = abonnementService.getByOwnerCompanyandName( abonneeId , bedrijf , naam );
        IAbonnee shareRecipient = new AbonneeService().getAbonneeById( targetAbonneeId );
        Boolean bSuccessful = abonnementService.shareWith( abonnementToShare , shareRecipient );

        if ( !bSuccessful ) {
            req.setAttribute( "errorMsg" , "Het is niet gelukt om het abonnement te delen." );
        } else {
            req.setAttribute( "successMsg" , "Het abonnement is gedeeld!" );
        }
        req.getRequestDispatcher( "/abonnementen" ).forward( req , resp );
    }
}
