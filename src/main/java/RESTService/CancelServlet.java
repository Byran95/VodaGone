package RESTService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anders Egberts on 14/10/2016.
 */
@WebServlet(
        urlPatterns = { "/cancelSubscription" }
)
public class CancelServlet extends HttpServlet {
    AbonnementService abonnementService = new AbonnementService();
    AbonneeService abonneeService = new AbonneeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int abonneeId = Integer.parseInt(req.getParameter("abonneeId"));
        String bedrijf = req.getParameter("bedrijf");
        String naam = req.getParameter("naam");

        abonnementService.cancelSubscription(abonneeId, bedrijf, naam);
        req.getRequestDispatcher( "/abonnementen" ).forward( req , resp );
    }
}
