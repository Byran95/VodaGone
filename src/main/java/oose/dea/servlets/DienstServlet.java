package oose.dea.servlets;

import com.google.inject.Inject;
import oose.dea.domain.IAbonnee;
import oose.dea.services.IDienstService;

import javax.inject.Singleton;
import javax.servlet.*;
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
public class DienstServlet extends HttpServlet {
    @Inject
    IDienstService dienstService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAbonnee loggedInUser = (IAbonnee) req.getSession().getAttribute( "loggedInUser" );
        if ( null == loggedInUser ) {
            req.getRequestDispatcher( "/login.jsp" ).forward( req , resp );
            return;
        }
        String searchTerm = req.getParameter( "searchTerm" );
        if ( null == searchTerm ) {
            req.setAttribute("diensten", dienstService.getAll());
        } else {
            req.setAttribute("diensten", dienstService.search( searchTerm ));
        }
        req.getRequestDispatcher( "/dienstUitproberenView.jsp" ).forward( req , resp );
    }
}