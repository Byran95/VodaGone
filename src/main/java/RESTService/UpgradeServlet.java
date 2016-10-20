package RESTService;

import DomainApplication.IAbonnement;
import DomainApplication.IDienst;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
@WebServlet(
        urlPatterns = { "/upgradeSubscription" }
)
public class UpgradeServlet extends HttpServlet {
    AbonnementService abonnementService = new AbonnementService();
    AbonneeService abonneeService = new AbonneeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean verdubbeld = Boolean.valueOf(req.getParameter("verdubbeld"));
        boolean verdubbeldReversed =! verdubbeld;
        int abonneeId = Integer.parseInt(req.getParameter("abonneeId"));
        String bedrijf = req.getParameter("bedrijf");
        String naam = req.getParameter("naam");

        System.out.println("verdubbeldReversed: " + verdubbeldReversed);
        abonnementService.updateVerdubbeld(verdubbeldReversed, abonneeId, bedrijf, naam);
        resp.sendRedirect(resp.encodeRedirectURL("/abonnementen"));

//        req.setAttribute( "abonnementen" , service.getAll() );
//        req.getRequestDispatcher( "/AbonnementView.jsp" ).forward( req , resp );
    }
}
