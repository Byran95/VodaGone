package oose.dea.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import oose.dea.domain.IAbonnee;
import oose.dea.services.IAbonneeService;

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
@Singleton
@WebServlet(
        urlPatterns = { "/login" }
)
public class LoginServlet extends HttpServlet {
    @Inject
    private IAbonneeService abonneeService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println( "Login servlet!!!!!1111111one");
        System.out.println( "Service: " + abonneeService );
        if ( null == req.getSession().getAttribute( "loggedInUser" ) ) {
            String userEmail = req.getParameter( "email" );
            if ( null == userEmail ) {
                System.out.println( "No email forwarding to login page");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                System.out.println( "Use da service");
                System.out.println( "Service: " + abonneeService );
                IAbonnee foundUser = abonneeService.getAbonneeByEmail(userEmail);
                System.out.println( "Post use");
                if ( null == foundUser ) {
                    //Error
                    req.setAttribute( "errorMsg" , "No such user" );
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                } else {
                    //Succes
                    req.getSession().setAttribute("loggedInUser", foundUser);
                    req.getRequestDispatcher("/abonnementen").forward(req, resp);
                }
            }
        } else {
            resp.getWriter().write( "You are already logged in!");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost( req , resp );
    }
}