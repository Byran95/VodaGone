package oose.dea;

import com.google.inject.servlet.ServletModule;
import oose.dea.controller.*;
import oose.dea.dao.FakeItemDAO;
import oose.dea.dao.ItemDAO;
import oose.dea.domain.IAbonneeAccess;
import oose.dea.domain.IAbonnementAccess;
import oose.dea.domain.IDienstAccess;
import oose.dea.domain.MySQLDataAccess.AbonneeDAOMySQL;
import oose.dea.domain.MySQLDataAccess.AbonnementDAOMySQL;
import oose.dea.domain.MySQLDataAccess.DienstDAOMySQL;
import oose.dea.services.IAbonneeService;
import oose.dea.services.IAbonnementService;
import oose.dea.services.IDienstService;
import oose.dea.services.ItemService;
import oose.dea.services.local.AbonneeService;
import oose.dea.services.local.AbonnementService;
import oose.dea.services.local.DienstService;
import oose.dea.services.local.LocalItemService;
import oose.dea.services.rest.AbonneeRestService;
import oose.dea.services.rest.AbonnementRestService;
import oose.dea.services.rest.DienstRestService;

/**
 * Created by Anders Egberts on 22/11/2016.
 */
public class AppBinding extends ServletModule {
    @Override
    protected void configureServlets() {
        super.configureServlets();
        serve("/viewItems").with(ItemsView.class);
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

        bind(ItemService.class).to(LocalItemService.class);
        bind(ItemDAO.class).to(FakeItemDAO.class);
        //Bind services
        boolean useRest = true;
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
