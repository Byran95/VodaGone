package DomainApplication.abonnement.servlets;

import DomainApplication.IAbonnee;
import DomainApplication.abonnee.AbonneeService;
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
        urlPatterns = { "/upgradeSubscription" }
)
public class DienstUpgradeServlet extends HttpServlet {
    AbonnementService abonnementService = new AbonnementService();
    AbonnementJerseyService jerseyService = new AbonnementJerseyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAbonnee loggedInUser = (IAbonnee) req.getSession().getAttribute( "loggedInUser" );
        if ( null == loggedInUser ) {
            req.getRequestDispatcher( "/login.jsp" ).forward( req , resp );
            return;
        }
        boolean verdubbeld = Boolean.valueOf(req.getParameter("verdubbeld"));
        int abonneeId = loggedInUser.getAbonneeId();
        String bedrijf = req.getParameter("bedrijf");
        String naam = req.getParameter("naam");

        if ( null == bedrijf || null == naam ) {
            return;
        }

        jerseyService.updateVerdubbeld( !verdubbeld, abonneeId, bedrijf, naam);
        resp.sendRedirect(resp.encodeRedirectURL("/abonnementen"));
    }
}