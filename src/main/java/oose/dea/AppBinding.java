package oose.dea;

import com.google.inject.servlet.ServletModule;
import oose.dea.abonnee.*;
import oose.dea.abonnement.*;
import oose.dea.dienst.*;

/**
 * Created by Anders Egberts on 22/11/2016.
 */
public class AppBinding extends ServletModule {
    @Override
    protected void configureServlets() {
        super.configureServlets();

        //Servlets
        serve("/login").with(LoginServlet.class);
        serve("/abonnementen").with(AbonnementServlet.class);
        serve("/shareService").with(DienstDelenServlet.class);
        serve("/startSharingService").with(DienstDelenStartServlet.class);
        serve("/cancelSubscription").with(DienstOpzeggenServlet.class);
        serve("/dienstUitproberen").with(DienstServlet.class);
        serve("/upgradeSubscription").with(DienstUpgradeServlet.class);
        serve("/tryService").with(ProbeerDienstServlet.class);
        serve("/register").with(RegisterAbonneeServlet.class);

        //Bind services
        boolean useRest = true; //TODO: Replace with settings-parameter
        if ( useRest ) {
            bind(IAbonnementService.class).to(AbonnementRestService.class);
            bind(IAbonneeService.class).to(AbonneeRestService.class);
            bind(IDienstService.class).to(DienstRestService.class);
        } else {
            bind(IAbonnementService.class).to(AbonnementService.class);
            bind(IAbonneeService.class).to(AbonneeService.class);
            bind(IDienstService.class).to(DienstService.class);
        }
        //Bind DAOs
        bind(IAbonneeAccess.class).to(AbonneeDAOMySQL.class);
        bind(IAbonnementAccess.class).to(AbonnementDAOMySQL.class);
        bind(IDienstAccess.class).to(DienstDAOMySQL.class);
    }
}
