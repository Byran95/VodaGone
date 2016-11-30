package oose.dea.abonnee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deze Servlet bevat geen markup-code (Eis: M2)
 *
 * Created by Anders Egberts on 20/10/2016.
 */
@WebServlet(
        urlPatterns = { "/logout" }
)
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute( "loggedInUser" , null );
        req.getRequestDispatcher("/").forward( req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
