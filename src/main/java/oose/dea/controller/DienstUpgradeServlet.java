package oose.dea.controller;

import com.google.inject.Inject;
import oose.dea.domain.IAbonnee;
import oose.dea.services.IAbonnementService;

import javax.inject.Singleton;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deze Servlet bevat geen markup-code (Eis: M2)
 *
 * Created by Anders Egberts on 14/10/2016.
 */
@Singleton
public class DienstUpgradeServlet extends HttpServlet {
    @Inject
    IAbonnementService abonnementService;

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

        abonnementService.updateVerdubbeld( !verdubbeld , abonneeId, bedrijf, naam);
        resp.sendRedirect(resp.encodeRedirectURL("/abonnementen"));
    }
}