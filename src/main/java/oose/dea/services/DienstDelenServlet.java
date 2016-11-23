package oose.dea.services;

import com.google.inject.Inject;
import oose.dea.domain.IAbonnee;
import oose.dea.domain.IAbonnement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deze Servlet bevat geen markup-code (Eis: M2)
 * Created by Anders Egberts on 20/10/2016.
 */
@WebServlet(
    value = "/shareService"
)
public class DienstDelenServlet extends HttpServlet {
    @Inject
    private IDienstService dienstService;
    @Inject
    private IAbonneeService abonneeService;
    @Inject
    private IAbonnementService abonnementService;

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

        IAbonnement abonnementToShare = abonnementService.getByOwnerCompanyandName( abonneeId , bedrijf , naam );
        IAbonnee shareRecipient = abonneeService.getAbonneeById( targetAbonneeId );
        Boolean bSuccessful = abonnementService.shareWith( abonnementToShare , shareRecipient );

        if ( !bSuccessful ) {
            req.setAttribute( "errorMsg" , "Het is niet gelukt om het abonnement te delen." );
        } else {
            req.setAttribute( "successMsg" , "Het abonnement is gedeeld!" );
        }
        req.getRequestDispatcher( "/abonnementen" ).forward( req , resp );
    }
}
