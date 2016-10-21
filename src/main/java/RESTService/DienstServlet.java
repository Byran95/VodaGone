package RESTService;

import DomainApplication.IAbonnee;

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
@WebServlet(
        urlPatterns = { "/dienstUitproberen" }
)
public class DienstServlet extends HttpServlet {
    DienstService dienstService = new DienstService();

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