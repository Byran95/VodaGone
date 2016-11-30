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
 * Created by Anders Egberts on 14/10/2016.
 */
@Singleton
//@WebServlet(
//        urlPatterns = { "/abonnementen" }
//)
public class AbonnementServlet extends HttpServlet {

    @Inject
    private IAbonnementService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAbonnee loggedInUser = (IAbonnee) req.getSession().getAttribute( "loggedInUser" );
        if ( null == loggedInUser ) {
            req.getRequestDispatcher( "/login.jsp" ).forward( req , resp );
            return;
        }
        req.setAttribute( "abonnementen" , service.getByAbonnee( loggedInUser.getAbonneeId() ) );
        req.getRequestDispatcher( "/AbonnementView.jsp" ).forward( req , resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet( req , resp );
    }
}
