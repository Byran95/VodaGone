package RESTService;

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
        urlPatterns = { "/abonnementen" }
)
public class AbonnementServlet extends HttpServlet {
    AbonnementService service = new AbonnementService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute( "abonnementen" , service.getAll() );
        req.getRequestDispatcher( "/AbonnementView.jsp" ).forward( req , resp );
    }
}
