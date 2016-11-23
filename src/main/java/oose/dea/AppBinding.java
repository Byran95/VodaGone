package oose.dea;

import com.google.inject.servlet.ServletModule;
import oose.dea.controller.ItemsView;
import oose.dea.dao.FakeItemDAO;
import oose.dea.dao.ItemDAO;
import oose.dea.services.IAbonneeService;
import oose.dea.services.IAbonnementService;
import oose.dea.services.IDienstService;
import oose.dea.services.ItemService;
import oose.dea.services.local.AbonneeService;
import oose.dea.services.local.AbonnementService;
import oose.dea.services.local.DienstService;
import oose.dea.services.local.LocalItemService;

/**
 * Created by Anders Egberts on 22/11/2016.
 */
public class AppBinding extends ServletModule {
    @Override
    protected void configureServlets() {
        System.out.println( "AppBinding" );
        super.configureServlets();
        serve("/viewItems").with(ItemsView.class);
        bind(ItemService.class).to(LocalItemService.class);
        bind(ItemDAO.class).to(FakeItemDAO.class);
        bind(IAbonnementService.class).to(AbonnementService.class);
        bind(IAbonneeService.class).to(AbonneeService.class);
        bind(IDienstService.class).to(DienstService.class);
    }
}
